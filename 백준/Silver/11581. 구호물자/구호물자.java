import java.util.Scanner;

/*
 * 백준 11581 - 구호물자
 * 
 * 주어지는 값
 * N : 교차로의 수
 * 각각 두 줄
 * 1. 연결된 교차로의 수
 * 2. 갈 수 있는 교차로의 번호
 * 
 * 대피소로 구호물자를 보내야 한다.
 * 
 * 특정 도로를 임의로 선택하면 이미 지나쳤던 교차로를 또 다시 방문할 수 없게
 * 
 * 1번 교차로에서 N번 교차로까지 가는 과정 중 지나쳤던 교차로를 다시 방문하는 경우가 생길 수 있으면
 * CYCLE
 * 그렇지 않다면
 * NO CYCLE
 * 
*/
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		boolean[][] dp = new boolean[N + 1][N + 1];
		
		//처음 인접행렬 만들기
		for(int i=1; i < N; i++) {
			int connectionCnt = sc.nextInt();
			for(int j=0; j < connectionCnt; j++) dp[i][sc.nextInt()] = true;
		}
		
		for(int k=1; k <= N; k++) { //경유지
			for(int i=1; i <= N; i++) { //출발지
				for(int j=1; j <= N; j++) { //도착지
					if(dp[i][k] && dp[k][j]) dp[i][j] = true;
				}
			}
		}
		
		String ans = "NO CYCLE";
		for(int i=1; i < N; i++) {
			if(dp[1][i] && dp[i][i]) {
				ans = "CYCLE";
				break;
			}
		}
		
		System.out.println(ans);
			
		sc.close();
	}
}