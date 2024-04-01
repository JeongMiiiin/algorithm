import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(sc.nextLine(), " ");
		for(int i=0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		int left = 0, right = 0, total = 0, result = 0;
		while(left < N) {
			if(total < M && right < N) total += arr[right++];
			else {
				if(total == M) result++;
				total -= arr[left++];
			}
		}
		System.out.println(result);
		sc.close();
	}
}