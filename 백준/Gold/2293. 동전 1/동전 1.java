import java.util.Scanner;

/*
 * 백준 2293 - 동전 1
 * 
 * n가지 종류의 동전
 * 각각의 동전이 나타내는 가치는 다르다.
 * 이 동전을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다.
*/
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] V = new int[N + 1];
		for(int i=1; i <= N; i++) V[i] = sc.nextInt();
		
		int[] dp = new int[K + 1];
		dp[0] = 1;
		for(int i=1; i <= N; i++) for(int j=V[i]; j <= K; j++) dp[j] += dp[j - V[i]];
		
		System.out.println(dp[K]);
		
		sc.close();
	}
}