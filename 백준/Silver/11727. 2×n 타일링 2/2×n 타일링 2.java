import java.util.Scanner;

/*
 * 백준 11727 - 2 x n 타일링 2
 * 
 * 2 x n 직사각형을 1 x 2, 2 x 1, 2 x 2 타일로 채우는 방법의 수
 * P[1] = 1;
 * P[2] = 3;
 * P[3] = 5;
 * P[4] = 11; 
 * 
 * 
*/
public class Main {
	
	static long[] P = new long[1001];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		System.out.println(find(N));
		sc.close();
	}
	private static long find(int n) {
		if(n == 1) P[n] = 1;
		if(n == 2) P[n] = 3;
		if(P[n] > 0) return P[n];
		
		if(n % 2 == 1) return P[n] = ((find(n - 1)) * 2 - 1) % 10007;
		else return P[n] = ((find(n - 1) + 1) * 2 - 1) % 10007;
	}
}