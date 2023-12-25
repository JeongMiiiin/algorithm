import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		
		long left = 0, right = n, result = Long.MAX_VALUE;
		while(left <= right) {
			long mid = (left + right) / 2;
			if(Math.pow(mid, 2) >= n) {
				result = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(result);
		
		sc.close();
	}
}