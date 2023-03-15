import java.util.Scanner;

/*
 * 백준 17626 - Four Squares
 * 
 * 라그랑주는 1770년에 모든 자연수는 넷 혹은 그 이하의 제곱수의 합으로 표현
 * 
 * 자연수 n이 주어질 때, n을 최소 개수의 제곱수 합으로 표현하는 컴퓨터 프로그램
 * 
 * DP로 풀기
 * 
*/
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] P = new int[50001];
		P[1] = 1;
		for(int i = 2; i <= N; i++) P[i] = Integer.MAX_VALUE;
		
		for(int i=2; i <= N; i++) for(int j=1; j * j <= i; j++) P[i] = Math.min(P[i], P[i - j * j] + 1);
		
		System.out.println(P[N]);
		sc.close();
	}
}