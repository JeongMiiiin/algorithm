import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int[] dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		for(int i=1; i <= N; i++) {
			int val = Integer.parseInt(st.nextToken()), init = i;
			dp[init] = Math.min(dp[init], val);
			while(init <= N) {
				dp[init] = Math.min(dp[init - i] + val, dp[init]);
				init++;
			}
		}
		
		System.out.println(dp[N]);
		sc.close();
	}
}