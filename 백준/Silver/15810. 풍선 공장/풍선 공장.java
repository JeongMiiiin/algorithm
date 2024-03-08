import java.util.Scanner;

public class Main {
	static long N;
	static long[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextLong();
		long M = sc.nextLong();
		arr = new long[(int) N];
		long min = 1000000;
		for(int i=0; i < N; i++) {
			arr[i] = sc.nextLong();
			min = Math.min(min, arr[i]);
		}
		
		long left = 0, right = min * M;
		while(left + 1 < right) {
			long mid = (left + right) / 2;
			if(calcCnt(mid) < M) left = mid;
			else right = mid;
		}
		System.out.println(right);
		sc.close();
	}
	static long calcCnt(long target) {
		long result = 0;
		for(int i=0; i < N; i++) result += target / arr[i];
		
		return result;
	}
}