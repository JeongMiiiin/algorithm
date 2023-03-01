import java.util.Scanner;

/*
 * 부분수열의 합
 * 
*/
public class Main {
	static int N, S, ans;
	static int[] inputs;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt();
		inputs = new int[N];
		for(int i=0; i < N; i++) inputs[i] = sc.nextInt();
		
		subset();
		
		System.out.println(ans);
		sc.close();
	}
	private static void subset() {
		for(int i=1; i < 1 << N; i++) {
			int sum = 0;
			for(int j=0; j < N; j++)
				if((i & 1 << j) != 0) sum += inputs[j];
			if(sum == S) ans++;
		}
		
	}
}
