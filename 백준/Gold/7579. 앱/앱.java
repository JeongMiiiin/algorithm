import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 7579 - 앱
 * 
 * 주어지는 값
 * N : 활성화된 앱의 수
 * M : 확보해야 하는 메모리
 * 두번째 줄 : 앱들이 사용중이 메모리
 * 세번째 줄 : 앱들을 비활성화했을 때의 비용
 * 
 * 앱들이 활성화 되어 있다는 것은 화면에 보이지 않더라도 메인 메모리에 직전의 상태가 기록되어 있는 것을 말한다.
 * 현재 N개의 앱이 활성화되어 있음
 * 이들 앱은 각각 Mi바이트 만큼의 메모리를 사용
 * 추가적으로 들어가는 비용 Ci라고 하자
 * 
 * 사용자가 새로운 앱 B를 실행하기 위하여, 추가로 M바이트의 메모리가 필요함.
 * 비용 ci의 합을 최소화하여 M바이트를 확보하는 방법
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		
		int[] V = new int[N + 1];
		int[] W = new int[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
		int max = 0;
		for(int i=1; i <= N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
			W[i] = Integer.parseInt(st2.nextToken());
			max += W[i];
		}
		
		int[][] dp = new int[N + 1][max + 1];
		
		for(int i=1; i <= N; i++) {
			for(int j=0; j <= max; j++) {
				if(W[i] > j) dp[i][j] = dp[i - 1][j];
				else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
			}
		}
		for(int i=0; i <= max; i++) {
			if(dp[N][i] >= M) {
				System.out.println(i);
				break;
			}
			
		}
		
		
	}
}