import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 9084 - 동전
 * 
 * 주어지는 값
 * T : 테스트 케이스 수
 * N : 동전의 가지 수
 * 두번째 줄 : 동전들의 각 금액
 * M : 주어진 금액
 * 
 * 동전의 종류가 주어질 때에 주어진 금액을 만드는 모든 방법을 세는 프로그램을 작성
 * 
 * Knapsack Problem
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] V = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i < N; i++) V[i] = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(br.readLine());
			
			int[] dp = new int[M + 1];
			dp[0] = 1;
			for(int i=0; i < N; i++) for(int j=V[i]; j <=M; j++) dp[j] += dp[j - V[i]];
			
			System.out.println(dp[M]);
		}
	}
}