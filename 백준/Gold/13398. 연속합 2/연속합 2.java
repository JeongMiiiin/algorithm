import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 13398 - 연속합 2
 * 
 * 주어지는 값
 * N : 수열의 길이
 * 두번째 줄 : 수열의 값들
 * 
 * n개의 정수로 이루어진 임의의 수열이 주어짐
 * 연속된 몇개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합
 * dp[i][j] -> i번에서 
 * 
 * 
*/
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] V = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i <= N; i++) V[i] = Integer.parseInt(st.nextToken());
		int[] dp = new int[N + 1];
		int[] dp2 = new int[N + 1];
		
		//첫번째부터 i번째까지의 연속합중 최댓값 저장
		dp[1] = V[1];
		for(int i=2; i <= N; i++) {
			dp[i] = V[i];
			dp[i] = Math.max(dp[i - 1] + V[i], dp[i]);
		}
		
		//마지막부터 i번째까지의 연속합중 최대값 저장
		dp2[N] = V[N];
		for(int i= N - 1; i >= 1; i--) {
			dp2[i] = V[i];
			dp2[i] = Math.max(dp2[i], dp2[i + 1] + dp2[i]);
		}
		
		//값을 제외하지 않고의 연속합 최대값 앞에서부터 찾기
		int ans = dp[1];
		for(int i=2; i <= N; i++) ans = Math.max(dp[i], ans);
		
		//i값을 제외하고 연속합 최대값 찾기
		for(int i=1; i <= N - 1; i++) ans = Math.max(dp[i - 1] + dp2[i + 1], ans);
		
		System.out.println(ans);
	}
	
}