import java.util.Scanner;

/*
 * 백준 9095번 - 1,2,3번 더하기
 * 주어진 수 n을 1,2,3의 합으로 나타내는 방법을 출력하라
 * 최소 1개 이상 사용해야함
*/
public class Main {
	static int N, ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=0; t < T; t++) {
			ans = 0;
			N = sc.nextInt();
			
			subset(0);
			System.out.println(ans);
		}
		
		sc.close();
	}
	private static void subset(int sum) {
		if(sum >= N) {
			if(sum == N) ans++;
			return;
		}
		
		subset(sum + 1); //1이 더해지는 경우
		subset(sum + 2); //2이 더해지는 경우
		subset(sum + 3); //3이 더해지는 경우
	}
}
