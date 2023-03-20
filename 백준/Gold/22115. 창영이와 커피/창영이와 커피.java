import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 22115 - 창영이와 커피
 * 
 * 주어지는 값
 * N : 커피의 개수
 * K : 섭취해야 하는 카페인의 양
 * 둘째 줄 : N개 커피의 카페인 함유량
 * 
 * 창영이는 커피를 좋아한다.
 * 회사에는 N개의 커피가 각각 1개씩 준비되어 있고, 각 커피에는 카페인 함유량 Ci가 있다.
 * 창영이는 N개의 커피 중 몇 개를 골라 정확히 K만큼의 카페인을 섭취하려고 한다.
 * 창영이가 정확히 K만큼의 카페인을 섭취하기 위해서는 최소 몇 개의 커피를 마셔야 하는지 출력
 * 
 * 각각마다 선택하거나 선택하지 않는 방법이 있음
 * 
*/
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int[] V = new int[N + 1];
		for(int i=1; i <= N; i++) V[i] = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N + 1][K + 1];
		Arrays.fill(dp[0], Integer.MAX_VALUE - N);
		dp[0][0] = 0;
		for(int i=1; i <= N; i++) {
			for(int j=1; j <= K; j++) {
				if(V[i] > j) dp[i][j] = dp[i - 1][j];
				else dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - V[i]] + 1);
			}
		} 
		
		int ans = -1;
		if(dp[N][K] != Integer.MAX_VALUE - N) ans = dp[N][K];
		
		System.out.println(ans);
		
	}
}