import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			int result = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			//맵 세팅
			int[][] map = new int[N][N];
			for(int i=0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			//상 우 하 좌 좌상 우상 좌하 우하
			int[] dr1 = {-1, 0, 1, 0, -1, -1, 1, 1};
			int[] dc1 = {0, 1, 0, -1, -1, 1, -1, 1};
			
			for(int i=0; i < N; i++) {
				for(int j=0; j < N; j++) {
					int temp = map[i][j];
					int d = 0, r = i, c = j;
					//상 우 하 좌 체크
					while(d < 4) {
						int cnt = 1;
						int nr = r + dr1[d];
						int nc = c + dc1[d];
						while(!outMap(nr, nc) && cnt < M) {
							temp += map[nr][nc];
							nr += dr1[d];
							nc += dc1[d];
							cnt++;
						}
						d++;
					}
					result = Math.max(temp, result);
					//좌상 우상 좌하 우하 체크
					temp = map[i][j];
					while(d < 8) {
						int cnt = 1;
						int nr = r + dr1[d];
						int nc = c + dc1[d];
						while(!outMap(nr, nc) && cnt < M) {
							temp += map[nr][nc];
							nr += dr1[d];
							nc += dc1[d];
							cnt++;
						}
						d++;
					}
					result = Math.max(temp, result);
				}
			}
			
			sb.append("#" + t + " " + result + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
}