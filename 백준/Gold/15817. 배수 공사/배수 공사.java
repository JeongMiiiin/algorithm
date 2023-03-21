import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 15817 - 배수 공사
 * 
 * 주어지는 값
 * N : 파이프의 종류
 * x : 만들어야 할 파이프의 길이
 * N개의 줄 : 파이프의 길이 Li, 수량 Ci
 * 
 * 직접 배수 공사를 하려고 함
 * 남은 파이프들을 적절히 모아서 원하는 길이로 만들어야 함
 * 가지고 있는 파이프의 길이와 그 수량을 알고 있을 때, 파이프를 적당히 합쳐서 x만큼의 길이를 만드는 방법의 수를 구하시오.
 * 
 * dp[i][j] -> i번째 파이프를 선택했을 때 j의 길이를 만드는 경우의 수
 * 
*/
public class Main {
	
	static int N, K;
	static int[] V, W;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		V = new int[N];
		W = new int[N];
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			V[i] = Integer.parseInt(st.nextToken());
			W[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N][K + 1];
		for(int i=0; i < N; i++) Arrays.fill(dp[i], -1);
		
		System.out.println(find(0,0));
	}
	
	private static int find(int start, int len) {
		if(len == K) return 1;
		if(len > K) return 0;
		if(start == N) return 0;
		
		int result = dp[start][len];
		if(result != -1) return result;
		
		result = 0;
		for(int n=0; n <= W[start]; n++) result += find(start + 1, len + V[start] * n);
		
		return dp[start][len]= result;
	}
}