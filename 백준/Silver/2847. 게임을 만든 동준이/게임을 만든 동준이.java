import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = sc.nextInt();
		int idx = 1;
		long result = 0;
		while(idx < N) {
			if(idx > 0 && arr[idx] <= arr[idx - 1]) {
				result += arr[idx - 1] - arr[idx] + 1;
				arr[idx - 1] -= (arr[idx - 1] - arr[idx] + 1);
				idx--;
			} else idx++;
		}
		
		System.out.println(result);
		sc.close();
	}
}