import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] arr = new int[M];
		for(int i=0; i < M; i++) arr[i] = sc.nextInt();
		Arrays.sort(arr);
		int total = 0, result = 0;
		for(int i=0; i < M; i++) {
			int cnt = i < M ? M - i : 1;
			if(cnt > N) cnt = N;
			if(total < arr[i] * cnt) {
				total = arr[i] * cnt;
				result = i;
			}
		}
		
		
		System.out.println(arr[result] + " " + total);
		sc.close();
	}
}