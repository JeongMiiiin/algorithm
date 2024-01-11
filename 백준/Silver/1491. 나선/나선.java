import java.util.Scanner;

public class Main {
	static int R, C;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		C = sc.nextInt();
		R = sc.nextInt();
		boolean[][] visited = new boolean[R][C];
		visited[R - 1][0] = true;
		int curR = R - 1, curC = 0, d = 1;
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		int idx = 0;
		while(idx++ < R * C - 1) {
			curR += dr1[d];
			curC += dc1[d];
			if(outMap(curR, curC) || visited[curR][curC]) {
				curR -= dr1[d];
				curC -= dc1[d];
				if(--d < 0) d = 3;
				curR += dr1[d];
				curC += dc1[d];
			}
			visited[curR][curC] = true;
		}
		System.out.println(curC + " " + (R - 1 - curR));
		sc.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
}