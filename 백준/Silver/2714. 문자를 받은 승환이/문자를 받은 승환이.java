import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int[][] map = new int[R][C];
			boolean[][] visited = new boolean[R][C];
			String s = st.nextToken();
			int idx = 0;
			for(int i=0; i < R; i++) for(int j=0; j < C; j++) map[i][j] = s.charAt(idx++) - '0';
			//우 하 좌 상
			int[] dr1 = {0, 1, 0, -1};
			int[] dc1 = {1, 0, -1, 0};
			StringBuilder temp = new StringBuilder();
			idx = 0;
			int total = 0, targetIdx = 0, d = 0, r = 0, c = 0;
			while(idx++ < R * C) {
				total += Math.pow(2, 4 - targetIdx++) * map[r][c];
				if(targetIdx > 4) { //단어로 변환해야 하는 경우
					if(total == 0) temp.append(" ");
					else {
						temp.append( (char) ((total - 1) + 'A') );
					}
					total = 0;
					targetIdx = 0;
				}
				visited[r][c] = true;
				r += dr1[d];
				c += dc1[d];
				if(r < 0 || r >= R || c < 0 || c >= C || visited[r][c]) {
					r -= dr1[d];
					c -= dc1[d];
					if(++d > 3) d = 0;
					r += dr1[d];
					c += dc1[d];
				}
			}
			sb.append(temp.toString().trim() + "\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}