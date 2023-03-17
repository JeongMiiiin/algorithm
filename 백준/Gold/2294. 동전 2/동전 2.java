import java.util.Arrays;
import java.util.Scanner;

/*
 * 백준 2294 - 동전 2
 * 
 * 주어지는 값
 * n : 동전의 가지 수
 * k : 목표 가치의 합
 * n번째 줄 : 동전들의 가치
 * 
 * 배낭 알고리즘
 * 
 * n가지 종류의 동전이 있고, 그 가치의 합이 k가 되도록 하고 싶음.
 * 최소의 동전을 쓰도록 하기
 * 
*/
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] V = new int[N + 1];
		for(int i=1; i <= N; i++) V[i]= sc.nextInt();
		
		int[] dp = new int[K + 1];
		
		Arrays.fill(dp, Integer.MAX_VALUE - 1);
		
		dp[0] = 0;
		for(int i=1; i <= N; i++) for(int j=V[i]; j <= K; j++) dp[j] = Math.min(dp[j], dp[j - V[i]] + 1);
		
		if(dp[K] == Integer.MAX_VALUE - 1) System.out.println(-1);
		else System.out.println(dp[K]);
		sc.close();
	}
}