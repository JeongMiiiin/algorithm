import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 12865 - 평범한 배낭
 * 
 * 준서가 여행에 필요하다고 생각하는 N개의 물건
 * 각 물건은 무게 W와 가치 V를 가지는데 해당 물건을 넣어서 가면 준서가 V만큼 즐길 수 있다.
 * 최대 K만큼의 무게를 가질 수 있다.
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] W = new int[N + 1];
		int[] V = new int[N + 1];
		int[][] dp = new int[N + 1][K + 1];
		
		for(int i=1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i <= N; i++) {
			for(int j=1; j <= K; j++) {
				if(W[i] > j) dp[i][j] = dp[i - 1][j];
				else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
			}
		}
		
		System.out.println(dp[N][K]);
	}
}