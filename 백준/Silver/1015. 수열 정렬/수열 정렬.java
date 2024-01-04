import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = sc.nextInt();
		boolean[] visited = new boolean[N];
		int[] temp = Arrays.copyOf(arr, N);
		Arrays.sort(temp);
		StringBuilder sb = new StringBuilder();
		for(int num : arr) {
			for(int i=0; i < N; i++) {
				if(temp[i] == num && !visited[i]) {
					visited[i] = true;
					sb.append(i + " ");
					break;
				}
			}
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}