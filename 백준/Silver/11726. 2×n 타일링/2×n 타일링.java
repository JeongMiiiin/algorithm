import java.util.Scanner;

/*
 * 백준 11726 - 2 x n 타일링
 * 
 * 2 x n 크기의 직사각형을 1 x 2, 2 x 1 타일로 태우는 방법의 수를 구하는 프로그램 작성
 * 
 * 1 : 1개
 * 2 : 2개
 * 3 : 3개
 * 4 : 5개
 * P(N) = P(N - 1) + P(N - 2);
*/
public class Main {
	static long[] P = new long[1001];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		System.out.println(find(N) % 10007);
		sc.close();
	}
	private static long find(int n) {
		if(n == 0) return P[0] = 0;
		if(n == 1) return P[1] = 1;
		if(n == 2) return P[2] = 2;
		if(P[n] > 0) return P[n];
		
		return P[n] = (find(n - 1) % 10007) + (find(n - 2) % 10007);
	}
}