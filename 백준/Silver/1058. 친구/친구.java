import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 백준 1058 - 친구
 * 
 * 주어지는 값
 * N : 사람의 수 ( <= 50)
 * 둘째줄부터 N개의 줄 - 각 사람들의 관계
 * Y - 친구, N - 친구가 아님
 * 
 * 가장 유명한 사람을 구하는 방법 -> 각 사람의 2-친구
 * 어떤 사람 A가 또 다른 사람 B의 2-친구가 되는 방법
 * 1. 두 사람이 친구
 * 2. 중간에 아는 사람이 있어야 함
 * 50 x 49 
*/
public class Main {
	static final int INF = 3000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int[][] dp = new int[K][K];
		//맵 세팅
		for(int i=0; i < K; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j=0; j < K; j++) {
				if(i == j) dp[i][j] = 0;
				else if(c[j] == 'N') dp[i][j] = INF;
				else dp[i][j] = 1;
			}
		}
		
		for(int k=0; k < K; k++) { //고려할 경유지
			for(int i=0; i < K; i++) { //출발지
				for(int j=0; j < K; j++) { //도착지
					if(j == k || i == j) continue;
					dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
				}
			}
		}
		
		int ans = 0;
		for(int i=0; i < K; i++) {
			int temp = 0;
			for(int j=0; j < K; j++) if(dp[i][j] > 0 && dp[i][j] < 3) temp++;
			ans = Math.max(ans, temp);
		}
		
		System.out.println(ans);
	}
}