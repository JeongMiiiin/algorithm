import java.util.Scanner;

/*
 * 백준 2579번 - 계단 오르기
 * 입력의 첫째줄에 계단의 개수가 주어짐
 * 
 * 계단 아래 시작점부터 꼭대기에 위치한 도착점까지 가는 게임
 * 각각의 계단에는 일정한 점수가 쓰여있음
 * 계단을 밟으면 그 점수를 획득
 * 계단 규칙
 * 1. 계단은 한번에 한 계단 또는 두 계단씩 가능
 * 2. 연속된 세 개의 계단을 모두 밟아서는 안됨.
 * 3. 마지막 도착 계단은 반드시 밟아야 함
*/
public class Main {
	static int N, ans;
	static int[] square;
	static int[] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //계단의 개수
		square = new int[N + 1];
		dp = new int[N + 1];
		for(int i=1; i <= N; i++) square[i] = sc.nextInt(); //계단 세팅
		
		goSquare();
		
		System.out.println(dp[N]);
		
		sc.close();
	}
	
	//계단오르는 방법 만들기
	private static void goSquare() {
		dp[1] = square[1];
		if(N >= 2) dp[2] = square[2] + square[1];
		
		for(int i=3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 2] , dp[i - 3] + square[i - 1]) + square[i];
		}
	}
}
