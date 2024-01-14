import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		boolean[][] visited = new boolean[R][C];
		char[][] map = new char[R][C];
		for(int i=0; i < R; i++) {
			int j=0;
			for(char c : br.readLine().toCharArray()) map[i][j++] = c;
		}
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		int sheep = 0, wolf = 0;
		for(int i=0; i < R; i++) {
			for(int j=0; j < C; j++) {
				if(map[i][j] == '#' || visited[i][j]) continue;
				visited[i][j] = true;
				int tempSheep = 0, tempWolf = 0;
				Stack<Point> stack = new Stack<>();
				stack.add(new Point(i, j));
				while(!stack.isEmpty()) {
					Point cur = stack.pop();
					if(map[cur.x][cur.y] == 'o') tempSheep++;
					if(map[cur.x][cur.y] == 'v') tempWolf++;
					for(int d=0; d < 4; d++) {
						int nr = cur.x + dr1[d];
						int nc = cur.y + dc1[d];
						if(outMap(nr, nc) || map[nr][nc] == '#' || visited[nr][nc]) continue;
						visited[nr][nc] = true;
						stack.add(new Point(nr, nc));
					}
				}
				if(tempSheep > tempWolf) sheep += tempSheep;
				else wolf += tempWolf;
			}
		}
		
		System.out.println(sheep + " " + wolf);
		br.close();
	}
	
	private static boolean outMap(int r, int c) {
		return ( r < 0 || r >= R || c < 0 || c >= C );
	}
}