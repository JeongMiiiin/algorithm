import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int score = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		if(N == 0) {
			System.out.println(1);
			return;
		}
		st = new StringTokenizer(sc.nextLine(), " ");
		for(int i=0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		int result = -1;
		if(N < P || score > arr[N - 1]) {
			result = 1;
			for(int i=0; i < N; i++) {
				if(score < arr[i]) result++;
				else break;
			}
		}
		System.out.println(result);
		sc.close();
	}
}