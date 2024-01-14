import java.awt.Point;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	static int R, C;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for(int t=1; t <= T; t++) {
			R = sc.nextInt();
			C = sc.nextInt();
			boolean[][] visited = new boolean[R][C];
			char[][] map = new char[R][C];
			sc.nextLine();
			for(int i=0; i < R; i++) {
				int j=0;
				for(char c : sc.nextLine().toCharArray()) map[i][j++] = c;
			}
			int result = 0;
			//상 우 하 좌
			int[] dr1 = {-1, 0, 1, 0};
			int[] dc1 = {0, 1, 0, -1};
			for(int i=0; i < R; i++) {
				for(int j=0; j < C; j++) {
					if(map[i][j] == '.' || visited[i][j]) continue;
					result++;
					Stack<Point> stack = new Stack<>();
					visited[i][j] = true;
					stack.add(new Point(i, j));
					while(!stack.isEmpty()) {
						Point cur = stack.pop();
						for(int d=0; d < 4; d++) {
							int nr = cur.x + dr1[d];
							int nc = cur.y + dc1[d];
							if(outMap(nr, nc) || map[nr][nc] == '.' || visited[nr][nc]) continue;
							visited[nr][nc] = true;
							stack.add(new Point(nr, nc));
						}
					}
				}
			}
			sb.append(result + "\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
	
	private static boolean outMap(int r, int c) {
		return ( r < 0 || r >= R || c < 0 || c >= C );
	}
}