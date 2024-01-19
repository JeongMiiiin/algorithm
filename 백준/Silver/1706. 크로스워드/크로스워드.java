import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int R, C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		for(int i=0; i < R; i++) {
			int j=0;
			for(char c : br.readLine().toCharArray()) map[i][j++] = c;
		}
		//하 우
		int[] dr1 = {1, 0};
		int[] dc1 = {0, 1};
		Set<String> set = new TreeSet<>();
		for(int i=0; i < R; i++) {
			for(int j=0; j < C; j++) {
				if(map[i][j] =='#') continue; //막힌 칸인 경우 패스
				StringBuilder sb = new StringBuilder();
				int d = 0;
				int nr = i - dr1[d];
				int nc = j - dc1[d];
				if(outMap(nr, nc) || map[nr][nc] == '#') { //시작점인 경우만
					nr += dr1[d];
					nc += dc1[d];
					while(!outMap(nr, nc) && map[nr][nc] != '#') {
						sb.append(map[nr][nc]);
						nr += dr1[d];
						nc += dc1[d];
					}
					if(sb.length() > 1) set.add(sb.toString());
				}
				d++;
				sb.setLength(0);
				nr = i - dr1[d];
				nc = j - dc1[d];
				if(outMap(nr, nc) || map[nr][nc] == '#') { //시작점인 경우만
					nr += dr1[d];
					nc += dc1[d];
					while(!outMap(nr, nc) && map[nr][nc] != '#') {
						sb.append(map[nr][nc]);
						nr += dr1[d];
						nc += dc1[d];
					}
					if(sb.length() > 1) set.add(sb.toString());
				}
			}
		}
		
		for(String s : set) {
			System.out.println(s);
			break;
		}
		br.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
}