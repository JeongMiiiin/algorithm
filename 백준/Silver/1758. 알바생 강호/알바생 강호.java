import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = sc.nextInt();
		Arrays.sort(arr);
		long result = 0;
		int order = 1;
		for(int i = N - 1; i >= 0; i--) {
			result += arr[i] - (order - 1) > 0 ? arr[i] - (order - 1) : 0;
			order++;
		}
		
		System.out.println(result);
		sc.close();
	}
}