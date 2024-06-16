import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(sc.nextLine());
		boolean[][] map = new boolean[R][C];
		//상 우 하 좌
		int[] dr1 = {1, 0, -1, 0};
		int[] dc1 = {0, 1, 0, -1};
		if(C * R >= K) {
			int curR = -1, curC = 0, cur = 0, d = 0;
			while(cur++ < K) {
				curR += dr1[d];
				curC += dc1[d];
				if(outMap(curR, curC) || map[curR][curC]) {
					curR -= dr1[d];
					curC -= dc1[d];
					if(++d > 3) d = 0;
					curR += dr1[d];
					curC += dc1[d];
					 
				}
				map[curR][curC] = true;
			}
			System.out.println(++curC + " " + ++curR);
		} else System.out.println(0);
		sc.close();
	}
	
	private static boolean outMap(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C; 
	}
}