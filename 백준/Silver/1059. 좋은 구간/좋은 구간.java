import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int L = sc.nextInt();
		int[] arr = new int[L];
		for(int i=0; i < L; i++) arr[i] = sc.nextInt();
		Arrays.sort(arr);
		int n = sc.nextInt();
		int result = 0;
		for(int i=0; i < L; i++) {
			int min = i > 0 ? arr[i - 1] + 1 : 1;
			int max = arr[i] - 1;
			if(n < min) break; //구간 최소 수가 n보다 클 경우 종료
			if(n > max) continue; //구간 최대 수가 n보다 작을 경우 패스
			while(min < max) {
				if(max < n) break;
				for(int j=min; j < max; j++) {
					if(j <= n) result++;
					else break;
				}
				max--;
			}
		}
		
		System.out.println(result);
		sc.close();
	}
}