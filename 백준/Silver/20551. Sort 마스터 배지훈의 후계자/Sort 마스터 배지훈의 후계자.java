import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = Integer.parseInt(sc.nextLine());
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < M; i++) {
			int target = Integer.parseInt(sc.nextLine());
			int left = 0, right = N - 1;
			while(left < right) {
				int mid = (left + right) / 2;
				if(target > arr[mid]) left = mid + 1;
				else right = mid;
			}
			if(arr[right] == target) sb.append(right + "\n");
			else sb.append(-1 + "\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
}