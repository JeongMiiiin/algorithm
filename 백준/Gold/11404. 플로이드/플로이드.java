import java.util.Arrays;
import java.util.Scanner;

/*
 * 백준 11404 - 플로이드
 * 
 * 주어지는 값
 * N : 도시의 개수
 * M : 버스의 개수
 * 셋째 줄 ~ M + 2번째 줄 : 버스의 정보
 * 버스 정보 -> a : 시작 도시, b : 도착 도시, c : 필요한 비용
 * 
 * n개의 도시가 있음.
 * 한 도시를 출발해서 다른 도시에 도착하는 m개의 버스가 있음.
 * 각 버스는 사용때마다 필요한 비용이 있음.
 * 모든 도시의 쌍 에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램 작성
*/
public class Main {
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //도시의 개수
		int M = sc.nextInt(); //버스의 개수
		
		long[][] dp = new long[N + 1][N + 1];
		
		//최솟값 비교를 위해 모두 INF로 채우기
		for(int i=1; i <= N; i++) Arrays.fill(dp[i], INF);
		
		
		//초기 버스 설정
		for(int i=1; i <= M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			dp[from][to] = Math.min(dp[from][to], sc.nextInt());
		}
		
		for(int k=1; k <= N; k++) { //경유지
			for(int i=1; i <= N; i++) { //출발지
				for(int j=1; j <= N; j++) { //도착지
					if(j == k && i == j) continue; //도착지가 경유지와 같을 때, 출발지와 도착지가 같을 때 패스
					dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i <= N; i++) {
			for(int j=1; j <= N; j++) {
				if(i == j || dp[i][j] >= INF) sb.append("0 ");
				else sb.append(dp[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
		sc.close();
	}
}