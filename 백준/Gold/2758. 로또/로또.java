import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 2758 - 로또
 * 
 * 주어지는 값
 * T : 테스트 케이스 수
 * N : 골라야하는 숫자의 개수
 * M : 범위의 최대 숫자
 * 
 * 로또는 1부터  m까지 숫자 중에 n개의 수를 고르는 로또
 * 
 * 각 숫자를 고를 때 이전에 고른 숫자보다 적어도 2배가 되도록 고르기 때문
 * 수를 고르는 방법의 수 만큼 로또를 구매하여, 같은 방법으로 2장 이상 구매하지 않는다.
 * 구매하는 로또의 개수를 출력
 * 
 * dp로 두배이상으로 올 수 있는 값 저장
 * 
 * Math.pow(2,i) <= target < Math.pow(2, i + 1) -> Math.pow(2, i - 1)에서 target / 2에 해당하는 값들이 올 수 있는 것
 * 최종 값은
 * Math.pow(2, n - 1) <= i  <= m
 * 만약 Math.pow(2,n - 1) > m 이면 경우의 수는 0
*/
public class Main {
	static int N, M;
	static long[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			dp = new long[N + 1][M + 1];
			
			for(int i=0; i <= M; i++) dp[0][i] = 1;
			
			for(int i=1; i <= N; i++) for(int j=1; j <= M; j++) dp[i][j] = dp[i - 1][j / 2] + dp[i][j - 1];
			
			bw.write(dp[N][M] + "\n");
		}
		
		bw.close();
	}
	
}