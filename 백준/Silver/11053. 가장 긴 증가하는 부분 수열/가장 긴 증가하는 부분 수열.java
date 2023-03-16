import java.util.Scanner;

/*
 * 백준 11053 - 가장 긴 증가하는 부분수열
 * 
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램 작성
*/
public class Main {
	static int N, ans = 0;
	static int[] inputs, P;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		inputs = new int[N];
		P = new int[N];
		for(int i=0; i < N; i++) inputs[i] = sc.nextInt();
		
		for(int i=0; i < N; i++) find(i);
		
		int max = P[0];
		for(int i=1; i < N; i++) max = Math.max(max, P[i]);
		System.out.println(max);
		
		sc.close();
	}
	private static int find(int cnt) {
		if(P[cnt] == 0) {
			P[cnt] = 1;
			
			for(int i= cnt - 1; i >= 0; i--) {
				if(inputs[i] < inputs[cnt]) {
					P[cnt] = Math.max(P[cnt], find(i) + 1);
					
				}
			}
		}
		return P[cnt];
	}
}