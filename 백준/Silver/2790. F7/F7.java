import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = sc.nextInt();
		Arrays.sort(arr);
		int result = 0, maxScore = arr[N - 1] + 1;
		for(int i = N - 1; i >= 0; i--) {
			if(arr[i] + N >= maxScore) result++;
			maxScore = Math.max(maxScore, arr[i] + N - 1 - i + 1);
		} 
		System.out.println(result);
		sc.close();
	}
}