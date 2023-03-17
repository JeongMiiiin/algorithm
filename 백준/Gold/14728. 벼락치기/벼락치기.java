import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 14728 - 벼락치기
 * 
 * 배낭 알고리즘
 * 
 * 주어지는 값
 * N : 단원 개수
 * T : 주어지는 총 시간
 * N번째 줄 : 예상 공부 시간 K, 배점 S
 * 
 * 벼락치기 준비 중
 * 1. 여러단원을 융합한 문제는 출제 X
 * 2. 한 단원에 한 문제를 출제한다. 단, 그 단원에 모든 내용을 알고 있어야 함
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N + 1][T + 1];
		
		int[] W = new int[N + 1];
		int[] V = new int[N + 1];
		for(int i=1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i <= N; i++) {
			for(int j=1; j <= T; j++) {
				if(W[i] > j) dp[i][j] = dp[i - 1][j];
				else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
			}
		}
		
		int max = 0;
		for(int i=1; i <= N; i++) max = Math.max(max, dp[i][T]);
		bw.write(max + "\n");
		
		bw.close();
	}
}