import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * SWEA 1263- 사람 네트워크2
 * 
 * 주어지는 값
 * T : 테스트 케이스 수
 * N : 사람 네트워크의 노드 수
 * 두번째 줄 : 사람 네트워크의 거리에 대한 값을 한 줄로 이어붙인 것
 * 
 * 사람의 네트워크에 대하여 여러가지 측정 방법을 사용하여 연구하기로 하였음
 * 이를 위해 우선 주어진 사람 네트워크에서 누가 가장 영향력이 있는 사람인지를 알 수 있는 척도로서 다음을 분석하는 프로그램을 작성하시오
*/
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int[][] dp = new int[N + 1][N + 1];
			
			int INF = Integer.MAX_VALUE / N;
			
			//초기 맵 세팅
			for(int i=1; i <= N; i++) {
				for(int j=1; j <= N; j++) {
					dp[i][j] = Integer.parseInt(st.nextToken());
					if(i != j && dp[i][j] == 0) dp[i][j] = INF;
				}
			}
			
			//플로이드워샬 알고리즘 실행
			for(int k=1; k <= N; k++) { //경유지
				for(int i=1; i <= N; i++) { //출발지
					for(int j=1; j <= N; j++) { //도착지
						if(i == j || j == k) continue; //경유지와 도착지가 같거나 출발지와 도착지가 같을 때 패스
						dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
					}
				}
			}
			
			int ans = Integer.MAX_VALUE;
			for(int i=1; i <= N; i++) {
				int temp = 0;
				for(int j=1; j <= N; j++) if(i != j && dp[i][j] < INF) temp += dp[i][j];
				
				ans = Math.min(ans, temp);
			}
			
			bw.write("#" + t + " " + ans + "\n");
		}
		
		br.close();
		bw.close();
	}
}