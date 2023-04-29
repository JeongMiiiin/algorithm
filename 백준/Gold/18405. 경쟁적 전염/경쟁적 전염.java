import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 18405 - 경쟁적 전염
 *
 * 주어지는 값
 * N : 맵 가로 세로 크기
 * K : 바이러스 종류
 * 2 ~ N + 1번째 줄 : 맵 상태
 * N + 2 : S, X, Y
 * 
 * N x N 크기의 시험관
 * 특정한 위치에는 바이러스가 존재할 수 있음
 * 모든 바이러스는 1 ~ K번까지의 바이러스 종류 중 하나
 * 
 * 시험관에 존재하는 모든 바이러스는 1초마다 4방향으로 증식
 * 매 초마다 번호가 낮은 종류의 바이러스부터 증식
 * 증식과정에서 특정한 칸에 이미 어떤 바이러스가 존재하면, 그 곳에는 다른 바이러스가 들어갈 수 없음
 * 
 * S초가 지난 후에 (X,Y)에 존재하는 바이러스의 종류 출력
 * 
*/
public class Main {
	static class Virus implements Comparable<Virus>{
		int category, r, c;
		
		public Virus(int category, int r, int c) {
			this.category = category;
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Virus o) {
			// TODO Auto-generated method stub
			return this.category - o.category;
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Virus> viruses = new PriorityQueue<>();
		int[][] map = new int[N + 1][N + 1];
		
		//맵 세팅
		for(int i=1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//바이러스가 있는 경우 우선순위큐에 담기
				if(map[i][j] > 0) viruses.add(new Virus(map[i][j], i, j));
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		
		outer : for(int i=0; i < S; i++) {
			int size = viruses.size();
			Queue<Virus> temp = new LinkedList<>();
			for(int j=0; j < size; j++) {
				Virus cur = viruses.poll();
				int nr, nc;
				for(int d=0; d < 4; d++) {
					nr = cur.r + dr1[d];
					nc = cur.c + dc1[d];
					//맵에서 벗어나거나 이미 해당 자리에 다른 바이러스가 있는 경우 패스
					if(nr < 1 || nr > N || nc < 1 || nc > N || map[nr][nc] > 0) continue;
					
					//증식 가능할 때에 표시
					map[nr][nc] = cur.category;
					temp.add(new Virus(cur.category, nr, nc));
				}
				temp.add(cur);
			}
			boolean flag = true;
			check : for(int x=1; x <= N; x++) {
				for(int y=1; y <= N; y++) {
					if(map[x][y] == 0) {
						flag = false;
						break check;
					}
				}
			}
			//이미 다 채운 경우
			if(flag) break outer;
			for(Virus v : temp) viruses.add(v);
		}		
		
		System.out.println(map[X][Y]);
		
	}
}