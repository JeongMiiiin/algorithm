import java.util.Scanner;

//백준 1978번 - 소수찾기
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		int ans = 0;
		outer : for(int i=0; i < T; i++) {
			int n = sc.nextInt();
			if(n < 2) continue; //n이 2보다 작을 시 건너뛰기
			for(int j=2; j <= Math.sqrt(n); j++) {
				if(n % j == 0) {
					continue outer;
				}
			}
			
			ans++;
		}
		
		System.out.println(ans);
	}
}
