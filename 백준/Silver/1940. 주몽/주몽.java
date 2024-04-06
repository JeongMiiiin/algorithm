import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int M = Integer.parseInt(sc.nextLine());
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		int left = 0, right = N - 1, total = arr[left] + arr[right], result = 0;
		while(left <= right) {
			if(total < M) {
				total -= arr[left++];
				if(right <= left) break;
				total += arr[left];
			} else if(total > M){
				total -= arr[right--];
				if(right <= left) break;
				total += arr[right];
			} else {
				result++;
				if(++left >= --right) break;
				total = arr[left] + arr[right];
			}
		}
		
		System.out.println(result);
		sc.close();
	}
}