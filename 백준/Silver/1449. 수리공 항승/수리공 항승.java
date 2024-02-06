import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int L = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = sc.nextInt();
		Arrays.sort(arr);
		int result = 0, prev = 0, remain = 0;
		for(int i=0; i < N; i++) {
			if(remain < arr[i] - prev) { //남은 테이브로 처리할 수 없을 때
				result++;
				remain = L - 1;
			} else remain -= arr[i] - prev;
			prev = arr[i];
		}
		
		System.out.println(result);
		sc.close();
	}
}