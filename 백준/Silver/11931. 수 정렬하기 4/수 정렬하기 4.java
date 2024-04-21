import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = Integer.parseInt(sc.nextLine());
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for(int i=N - 1; i >= 0; i--) sb.append(arr[i] + "\n");
		
		System.out.println(sb.toString());
		sc.close();
	}
}