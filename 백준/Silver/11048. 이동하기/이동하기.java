import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(sc.nextLine(), " ");
			for(int j=0; j < M; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}
		//우 하 대각선
		int[] dr1 = {0, 1, 1};
		int[] dc1 = {1, 0, 1};
		
		int[][] dp = new int[N][M];
		for(int i=0; i < N; i++) Arrays.fill(dp[i], -1);
		Queue<Point> q = new LinkedList<>();
		dp[0][0] = arr[0][0];
		q.add(new Point(0, 0));
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for(int d=0; d < 3; d++) {
				int nr = cur.x + dr1[d];
				int nc = cur.y + dc1[d];
				if(!outMap(nr, nc) && dp[nr][nc] < dp[cur.x][cur.y] + arr[nr][nc]){
					dp[nr][nc] = dp[cur.x][cur.y] + arr[nr][nc];
					q.add(new Point(nr,nc));
				}
			}
			
		}
		
		System.out.println(dp[N - 1][M - 1]);
		sc.close();
	}
	
	private static boolean outMap(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}
	
}