import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] ropes = new int[N];
		for(int i=0; i < N; i++) ropes[i] = sc.nextInt();
		Arrays.sort(ropes);
		int result = 0;
		int len = N;
		for(int i=0; i < N; i++) result = Math.max(ropes[i] * len--, result);
		
		System.out.println(result);
		sc.close();
	}
}