import java.util.Scanner;

/*
 * 백준 9461 - 파도반 수열
 * 
 * 나선모양의 삼각형
 * 첫 삼각형은 정삼각형으로 변의 길이 1
 * 
 * 파도반 수열 P(N)은 나선에 있는 정삼각형 변의 길이
 * 1, 1, 1, 2, 2, 3, 4, 5, 7, 9, 12, 21
 * 
 * P(N) -> P(N - 2) + P(N - 3) 없으면 0으로
*/
public class Main {
	static long[] P = new long[101];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1; t <= T; t++) {
			int N = sc.nextInt();
			System.out.println(find(N));
		}
		
		sc.close();
	}
	private static long find(int n) {
		if(n == 0) return P[0] = 0;
		if(n == 1) return P[1] = 1;
		if(n == 2) return P[2] = 1;
		if(P[n] > 0) return P[n];
		return P[n] = find(n - 2) + find(n - 3);
	}
}