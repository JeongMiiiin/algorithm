import java.util.Scanner;

public class Main {
	static int R, C;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		boolean[][] visited = new boolean[R][C];
		int result = 0;
		char[][] map = new char[R][C];
		sc.nextLine();
		for(int i=0; i < R; i++) {
			int j=0;
			for(char c : sc.nextLine().toCharArray()) map[i][j++] = c;
		}
		//우 하
		int[] dr1 = {0, 1};
		int[] dc1 = {1, 0};
		for(int i=0; i < R; i++) {
			for(int j=0; j < C; j++) {
				if(visited[i][j]) continue;
				result++;
				visited[i][j] = true;
				int d = map[i][j] == '-' ? 0 : 1;
				int nr = i + dr1[d];
				int nc = j + dc1[d];
				while(!outMap(nr, nc) && !visited[nr][nc] && map[nr][nc] == map[i][j]) {
					visited[nr][nc] = true;
					nr += dr1[d];
					nc += dc1[d];
				}
			}
		}
		
		System.out.println(result);
		sc.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
}