import java.util.Scanner;

public class Main {
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int target = sc.nextInt();
		int[][] map = new int[N][N];
		
		int start = (int) Math.pow(N, 2);
		
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		int r = 0, c = 0, d = 2, targetR = 0, targetC = 0;
		while(start > 0) {
			if(target == start) {
				targetR = r;
				targetC = c;
			}
			map[r][c] = start--;
			r += dr1[d];
			c += dc1[d];
			if(outMap(r, c) || map[r][c] != 0) {
				r -= dr1[d];
				c -= dc1[d];
				if(--d < 0) d = 3;
				r += dr1[d];
				c += dc1[d];
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N; i++) {
			for(int j=0; j < N; j++) sb.append(map[i][j] + " ");
			sb.append("\n");
		}
		sb.append(++targetR + " " + ++targetC);
		System.out.println(sb.toString());
		sc.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
}