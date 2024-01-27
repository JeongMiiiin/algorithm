import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = sc.nextInt();
		int result = 0, cur = 0;
		for(int i = 0; i < N; i++) {
			if(cur + arr[i] > M) {
				result++;
				cur = arr[i];
			} else cur += arr[i];
		}
		if(cur > 0) result++;
		
		System.out.println(result);
		sc.close();
	}
}