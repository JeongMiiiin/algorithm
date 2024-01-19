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
		for(int i=0; i < N; i++) result += Math.abs(i + 1 - arr[i]);
		System.out.println(result);
		sc.close();
	}
}