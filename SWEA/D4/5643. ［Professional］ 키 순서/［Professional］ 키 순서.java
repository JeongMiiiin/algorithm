import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * SWEA 5643 - 키 순서
 * 
 * 주어지는 값
 * T : 테스트 케이스 수
 * N : 학생들의 수
 * M : 키를 비교한 횟수
 * 세번째 줄 ~ M번째 줄 : 비교 정보 (a < b)
 * 
 * 
 * 1번부터 N번까지 번호가 붙여져 있는 학생들에 대하여 두 학생끼리 키를 비교한 결과의 일부가 주어져 있음.
 * N명의 학생들의 키는 모두 다름
 * 
 * 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇 명인지 계산하여 출력하는 프로그램 작성
 * 
 * 플로이드 워샬?
*/
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		final int INF = 3000;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] dp = new int[N + 1][N + 1];
			int M = Integer.parseInt(br.readLine());
			StringTokenizer st;
			//비교한 상황 표시
			for(int i=0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				dp[from][to] = 1;
			}
			
			//현재 직접 연결되어있지 않은 곳은 표시해주기
			for(int i=1; i <= N; i++) for(int j=1; j <= N; j++) if(i != j && dp[i][j] == 0) dp[i][j] = INF;
			
			for(int k=1; k <= N; k++) { //경유지
				for(int i=1; i <= N; i++) { //출발지
					for(int j=1; j <= N; j++) { //도착지
						if(j == k || i == j) continue; //도착지가 출발지나 경유지와 같을 때 패스
						dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
					}
				}
			}
			
			int ans = 0;
			for(int i=1; i <= N; i++) {
				//내 앞과 뒤에 위치한 학생들 수 세기
				int beforeCnt = 0, afterCnt = 0;
				for(int j=1; j <= N; j++) {
					if(i != j && dp[i][j] < INF) beforeCnt++;
					if(i != j && dp[j][i] < INF) afterCnt++;
				}
				
				if(beforeCnt + afterCnt == N - 1) ans++;
				
			}
			//출력
			bw.write("#" + t + " " + ans + "\n");
		}
		br.close();
		bw.close();
	}
}