import java.util.Scanner;

/*
 * 백준 2839번 - 설탕배달
 * 주어지는 값
 * N : 옮겨야하는 킬로그램
 * 3, 5를 조합하여 N킬로그램을 최대한 적게 옮기고, 정확하게 N을 만들 수 없다면 -1 출력
*/
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int ans = -1;
		if(N / 5 >= 1) {
			for(int i = N / 5; i > -1; i --) {
				int temp = N;
				temp -= (5 * i);
				if( temp % 3 == 0) {
					int j = temp / 3;
					ans = i + j;
					break;
				}
			}
		} else {
			if( N % 3 == 0) ans = 1;
		}
		System.out.println(ans);
		sc.close();
	}
}
