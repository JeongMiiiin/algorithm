import java.util.Scanner;

/*
 * 백준 12888 - 완전 이진 트리 도로 네트워크
 * 
 * BOJ나라의 도로 네트워크는 완전 이진 트리의 형태를 가짐.
 * 도로 네트워크 트리의 높이 H를 알고 있다.
 * 도시의 개수 -> 2(H + 1)승 - 1개
 * 도로의 개수 -> 2(H + 1)승 - 2개
 * 도로 네트워크에 차를 보내려고 한다.
 * 모든 차는 시작 도시와 도착 도시가 있으며, 같은 도시를 두 번 이상 방문 하지 않는다.
 * 차의 시작도시와 도착 도시가 같을 수도 있다.
 * 모든 도시를 방문한 차의 개수가 모두 1개가 되기 위해서는 차를 최소 몇 대 보내야 하는지 구하라.
 * dp[H] = dp[H - 2] + 2(H - 1)승
*/
public class Main {
	static long[] dp = new long[61];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int H = sc.nextInt();
		
		dp[0] = 1;
		dp[1] = 1;
		
		find(H);
		
		System.out.println(dp[H]);
		sc.close();
	}
	private static long find(int h) {
		if(h < 2 || dp[h] > 0) return dp[h];
		
		return dp[h] = ((long) Math.pow(2, h - 1)) + find(h - 2);
	}
}