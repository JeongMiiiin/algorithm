import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) arr[i] = sc.nextInt();
		
		int prev = 1;
		int result = 0;
		if(arr[0] > 1) {
			result += arr[0] - 1;
			prev = arr[0];
		}
		
		for(int i = 1; i < N; i++) {
			if(prev + 1 < arr[i]) {
				result += arr[i] - prev;
				prev = arr[i];
			}
			
			if(prev + 1 == arr[i]) prev = arr[i];
		}
		System.out.println(result);
		sc.close();
	}
}