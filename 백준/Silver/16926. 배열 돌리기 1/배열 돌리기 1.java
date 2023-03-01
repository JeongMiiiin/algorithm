import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 16926번 - 배열돌리기 1
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int R;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		//입력
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		//입력
		map = new int[N][M];
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//배열을 R번 회전시켜라
		//방향을 지원하는 배열들 (하,우,상,좌)
		int[] dr1 = {1,0,-1,0};
		int[] dc1 = {0,1,0,-1};
		
		//회전을 하는 배열의 수
		for(int i=0; i < (Math.min(N, M) / 2); i++) {
			for(int j=0; j < R; j++) {
				int dir = 0;
				int r = i;
				int c = i;
				int prev = map[r][c];
				//방향을 다 돌았을 때 while문 반복 끝
				while(dir < 4) {
					r += dr1[dir];
					c += dc1[dir];
					//값을 벗어날 때
					if(r == (N - i) || r < i || c == (M - i) || c < i) {
						r -= dr1[dir];
						c -= dc1[dir];
						dir++;
						if(dir == 4) break;
						r += dr1[dir];
						c += dc1[dir];
					}
					int tempPrev = map[r][c]; 
					map[r][c] = prev;
					prev = tempPrev;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N; i++) {
			for(int j=0; j < M; j++) sb.append(map[i][j] + " ");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
}
