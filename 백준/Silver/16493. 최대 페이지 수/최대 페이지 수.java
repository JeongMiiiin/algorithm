import java.util.Scanner;

/*
 * 백준 16493 - 최대 페이지 수
 * 
 * 주어지는 값
 * N : 남은 기간
 * M : 챕터의 수
 * 
*/
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] dp = new int[M + 1][N + 1];
		
		int[] W = new int[M + 1];
		int[] V = new int[M + 1];
		
		for(int i=1; i <= M; i++) {
			W[i] = sc.nextInt();
			V[i] = sc.nextInt();
		}
		
		for(int i=1; i <= M; i++) {
			for(int j=1; j<= N; j++) {
				if(W[i] > j) dp[i][j] = dp[i - 1][j];
				else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
			}
		}
		
		System.out.println(dp[M][N]);
 		
		sc.close();
	}
}