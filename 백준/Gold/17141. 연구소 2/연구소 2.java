import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 17141 - 연구소 2
 * 
 * N x N 크기의 연구소
 * 바이러스가 유출되려고 함.
 * 연구소 특정 위치에 바이러스 M개를 놓을 것이고, 신호와 동시에 바이러스는 퍼져나감.
 * 연구소는 빈 칸, 벽으로 이루어짐.
 * 빈 칸은 바이러스를 놓을 수 있음
 * 바이러스는 상하좌우로 인접한 모든 빈 칸으로 복제되며, 1초가 걸림
 * 연구소 상태
 * 0 : 빈 칸, 1 : 벽, 2 : 바이러스가 가능한 곳
 * 모든 빈 칸에 바이러스가 있게 되는 최소 시간을 출력.
 * 어떻게 놓아도 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우에는 -1 출력
 * 
 * 연구소 맵을 그리고
 * 바이러스의 조합을 만들고, 해당 조합으로 시뮬레이션 돌리기
*/
public class Main {
	static class Virus {
		int r, c;
		public Virus(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M, ans = Integer.MAX_VALUE;
	static int[][] originMap;
	static List<Virus> virusList = new ArrayList<>();
	static Virus[] numbers;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new Virus[M];
		//맵 세팅
		originMap = new int[N][N];
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < N; j++) {
				originMap[i][j] = Integer.parseInt(st.nextToken());
				if(originMap[i][j] == 2) { //바이러스가 있을 수 있는 곳이면
					virusList.add(new Virus(i, j));
					originMap[i][j] = 0;
				}
			}
		}
		
		comb(0, 0);
		
		if(ans == Integer.MAX_VALUE) ans = -1;
		System.out.println(ans);
	}
	private static void comb(int cnt, int start) {
		if(cnt == M) { //조합이 완성되면 시뮬레이션
			simulate();
			return;
		}
		
		for(int i= start; i < virusList.size(); i++) {
			numbers[cnt] = virusList.get(i);
			comb(cnt + 1, i + 1);
		}
	}
	
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	
	//시뮬레이션
	private static void simulate() {
		
		int[][] testMap = new int[N][N];
		
		copyMap(testMap);
		
		Queue<Virus> q = new LinkedList<>();
		
		for(int i=0 ; i < M; i++) {
			testMap[numbers[i].r][numbers[i].c] = 2;
			q.offer(numbers[i]);
		}
		
		int time = -1;
		
		while(!q.isEmpty()) {
			int size = q.size();
			Virus cur;
			int nr, nc;
			for(int i=0; i < size; i++) {
				cur = q.poll();
				for(int d=0; d < 4; d++) {
					nr = cur.r + dr1[d];
					nc = cur.c + dc1[d];
					//맵을 벗어나지 않고 빈 칸인 경우
					if( !outMap(nr, nc) && testMap[nr][nc] == 0) {
						testMap[nr][nc] = 2;
						q.offer(new Virus(nr, nc));
					}
					
				}
			}
			
			time++;
		}
		
		boolean flag = true;
		for(int i=0; i < N; i++) {
			for(int j=0; j < N; j++) {
				if(testMap[i][j] == 0) {
					flag = false;
					break;
				}
			}
		}
		//다 퍼져 빈칸이 없는 경우
		if(flag) ans = Math.min(ans, time); 
	}
	
	private static void copyMap(int[][] newMap) {
		for(int i=0; i < N; i++) System.arraycopy(originMap[i], 0, newMap[i], 0, N);
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
	
}