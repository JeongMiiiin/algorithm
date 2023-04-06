import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 17142 - 연구소 3
 * 
 * N x N 크기의 연구소
 * 바이러스를 유출하려 함
 * 바이러스는 활성 상태와 비활성 상태가 있음
 * 연구소는 빈 칸, 벽으로 이루어짐.
 * 빈 칸은 바이러스를 놓을 수 있음.
 * 바이러스는 상하좌우로 인접한 모든 빈 칸으로 복제되며, 1초가 걸림
 * 비활성화된 바이러스를 만나면 해당 바이러스를 활성화시키고 바로 복제 시작
 * 연구소 상태
 * 0 : 빈 칸, 1 : 벽, 2 : 바이러스가 가능한 곳
 * 모든 빈 칸에 바이러스가 있게 되는 최소 시간을 출력
 * 어떻게 놓아도 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우에는 -1 출력
 * 
*/
public class Main {
	static class Virus{
		int r, c;
		public Virus(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M, ans = Integer.MAX_VALUE;
	static int[][] originMap;
	static List<Virus> inputs = new ArrayList<>();
	static Virus[] numbers;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new Virus[M];
		
		//맵세팅
		originMap = new int[N][N];
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < N; j++) {
				originMap[i][j] = Integer.parseInt(st.nextToken());
				//바이러스가 있는 곳인 경우
				if(originMap[i][j] == 2) inputs.add(new Virus(i, j));
			}
		}
		
		comb(0, 0);
		
		
		if(ans == Integer.MAX_VALUE) ans = -1;
		System.out.println(ans);
		br.close();
	}
	private static void comb(int cnt, int start) {
		if(cnt == M) {
			simulate();
			return;
		}
		
		for(int i=start; i < inputs.size(); i++) {
			numbers[cnt] = inputs.get(i);
			comb(cnt + 1, i + 1);
		}
		
	}
	
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	private static void simulate() {
		int[][] testMap = new int[N][N];
		copyMap(testMap);
		
		Queue<Virus> q = new LinkedList<>();
		
		//활성화된 바이러스 표시
		for(int i=0; i < M; i++) {
			q.offer(numbers[i]);
			testMap[numbers[i].r][numbers[i].c] = 3;
		}
		
		int time = 0;
		
		while(!q.isEmpty()) {
			//빈 칸이 없으면 종료
			if(!checkMap(testMap)) break;
			
			time++; //시간 올리기
			
			int size = q.size();
			Virus cur;
			int nr, nc;
			for(int i=0; i < size; i++) {
				cur = q.poll();
				for(int d=0; d < 4; d++) {
					nr = cur.r + dr1[d];
					nc = cur.c + dc1[d];
					//맵을 벗어났거나 벽을 만났거나 이미 활성화된 바이러스가 있다면 패스
					if(outMap(nr, nc) || testMap[nr][nc] == 1 || testMap[nr][nc] == 3) continue;
					
					q.offer(new Virus(nr, nc));
					
					//활성화된 바이러스로 표시
					testMap[nr][nc] = 3;
				}
			}
		}
		
		//빈칸이 없을 경우
		if(!checkMap(testMap)) ans = Math.min(ans, time);
		
	}
	
	//빈칸이 있을 경우 true
	private static boolean checkMap(int[][] newMap) {
		for(int i=0; i < N; i++) for(int j=0; j < N; j++) if(newMap[i][j] == 0) return true;
		return false;
	}
	
	private static void copyMap(int[][] newMap) {
		for(int i=0; i < N; i++) System.arraycopy(originMap[i], 0, newMap[i], 0, N);
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
}