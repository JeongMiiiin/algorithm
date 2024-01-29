import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		double[] arr = new double[N];
		for(int i=0; i < M; i++) {
			for(int j=0; j < N; j++) {
				int idx = sc.nextInt() - 1;
				arr[idx] = Math.max(arr[idx], sc.nextDouble());
			}
		}
		Arrays.sort(arr);
		double result = 0;
		for(int i= N - 1; i >= N - K; i--) result += arr[i];
		
		System.out.println(String.format("%.1f", result));
		sc.close();
	}
}