import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = sc.nextInt() - sc.nextInt();
		Arrays.sort(arr);
		int result = 0;
		if(N % 2 == 1) result = 1;
		else result = Math.abs(arr[N / 2] - arr[N / 2 - 1] + 1);
		
		System.out.println(result);
		sc.close();
	}
}