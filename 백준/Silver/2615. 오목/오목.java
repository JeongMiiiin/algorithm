import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[19][19];
		StringTokenizer st;
		//맵 세팅
		for(int i=0; i < 19; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < 19; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		//좌하 우하 우 하
		int[] dr1 = {1, 1, 0, 1};
		int[] dc1 = {-1, 1, 1, 0};
		
		//우승자 판별 변수
		int winner = 0, r = 0, c = 0;
		//검은색 판단
		outer : for(int i=0; i < 19; i++) {
			for(int j=0; j < 19; j++) {
				if(map[i][j] != 1) continue;
				
				for(int d=0; d < 4; d++) {
					int temp = 1;
					int nr = i - dr1[d];
					int nc = j - dc1[d];
					while(!outMap(nr, nc) && map[nr][nc] == 1) {
						temp++;
						nr -= dr1[d];
						nc -= dc1[d];
					}
					
					nr = i + dr1[d];
					nc = j + dc1[d];
					while(!outMap(nr,nc) && map[nr][nc] == 1) {
						temp++;
						nr += dr1[d];
						nc += dc1[d];
					}
					
					if(temp == 5) {
						winner = 1;
						if(d > 0) {
							r = i;
							c = j;
						} else {
							r = nr - dr1[d];
							c = nc - dc1[d];
						}
						
						break outer;
					}
				}
			}
		}
		
		if(winner == 0) { //검은색은 못 이길 떄
			outer : for(int i=0; i < 19; i++) {
				for(int j=0; j < 19; j++) {
					if(map[i][j] != 2) continue;
					
					for(int d=0; d < 4; d++) {
						int temp = 1;
						int nr = i - dr1[d];
						int nc = j - dc1[d];
						while(!outMap(nr, nc) && map[nr][nc] == 2) {
							temp++;
							nr -= dr1[d];
							nc -= dc1[d];
						}
						
						nr = i + dr1[d];
						nc = j + dc1[d];
						while(!outMap(nr,nc) && map[nr][nc] == 2) {
							temp++;
							nr += dr1[d];
							nc += dc1[d];
						}
						
						if(temp == 5) {
							winner = 2;
							if(d > 0) {
								r = i;
								c = j;
							} else {
								r = nr - dr1[d];
								c = nc - dc1[d];
							}
							break outer;
						}
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(winner);
		if(winner > 0) sb.append("\n" + (r + 1) + " " + (c + 1));
		System.out.println(sb.toString());
		br.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= 19 || c <0 || c >= 19);
	}
}