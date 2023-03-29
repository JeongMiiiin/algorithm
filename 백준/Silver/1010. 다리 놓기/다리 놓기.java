import java.util.Scanner;

/*
 * 백준 1010 - 다리 놓기
 * 
 * mCn 문제
 * 
 * m! / n!(m - n)!
 * 
 * 
 * 도시에는 동쪽과 서쪽으로 나누는 큰 일직선 모양의 강이 흐르고 있음.
 * 다리를 지을 예정
 * 다리를 짓기에 적합한 곳 -> 사이트
 * 서쪽에는 N개, 동쪽에는 M개의 사이트 존재 (N <= M)
 * 서쪽의 사이트 개수만큼 다리를 지으려 한다.
 * 서로 겹쳐질 수 없다고 할 때 다리를 지을 수 있는 경우의 수를 구하라.
*/
public class Main {
	static double[] dp = new double[31];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		dp[0] = 1;
		dp[1] = 1;
		int T = sc.nextInt();
		for(int t=1; t <= T; t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			System.out.printf("%.0f\n", find(M) / (find(N) * find(M - N)));
		}
		
		sc.close();
	}
	private static double find(int m) {
		if(dp[m] > 0) return dp[m];
		return dp[m] = m * find(m - 1);
	}
}