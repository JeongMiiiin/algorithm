import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(sc.nextLine(), " ");
		for(int i=0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		int temp = 0;
		for(int i=0; i < K; i++) temp += arr[i];
		int result = temp;
		for(int i=K; i < N; i++) {
			temp += arr[i];
			temp -= arr[i - K];
			result = Math.max(temp, result);
		}
		
		System.out.println(result);
		sc.close();
	}
}