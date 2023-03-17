import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 1106 - 호텔
 * 
 * 홍보를 할 수 있는 도시가 주어짐
 * 각 도시별로 홍보하는데 드는 비용과 그 때 몇명의 호텔 고객이 늘어나는지에 대한 정보가 있다.
 * 
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] W = new int[N + 1];
		int[] V = new int[N + 1];
		int max = 0;
		for(int i=1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
			max += W[i];
		}
		
		int[] dp = new int[C + 101];
		
		Arrays.fill(dp, Integer.MAX_VALUE - max);
		
		dp[0] = 0;
		for(int i=1; i <= N; i++) for(int j=V[i]; j < C + 101; j++) dp[j] = Math.min(dp[j], dp[j - V[i]] + W[i]);
		
		int ans = Integer.MAX_VALUE;
		for(int i=C; i < C + 101; i++) ans = Math.min(ans, dp[i]); 
		
		System.out.println(ans);
		
	}
}