import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = sc.nextInt();
		Arrays.sort(arr);
		int max = 5;
		for(int i=0; i < N; i++) {
			int prev = arr[i], cnt = 1, addCnt = 0, startIdx = i + 1;
			while(cnt < 5) {
				if(startIdx < N && prev + 1 == arr[startIdx]) startIdx++;
				else addCnt++;
				prev++;
				cnt++;
			}
			max = Math.min(max, addCnt);
		}
		
		System.out.println(max);
		sc.close();
	}
}