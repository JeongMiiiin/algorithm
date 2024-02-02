import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long S = sc.nextLong();
		long result = 0;
		long total = 0;
		long num = 1;
		while(total < S) {
			result++;
			total += num;
			if(total > S) {
				result--;
				total = S;
			}
			num++;
		}
		System.out.println(result);
		sc.close();
	}
}