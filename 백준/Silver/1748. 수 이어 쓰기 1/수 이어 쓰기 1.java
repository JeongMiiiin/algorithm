import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int result = 0;
		while(N > 0) {
			int digit = (int) (Math.log10(N) + 1); //몇자리 수인지 구하기
			int cnt = N - (int) Math.pow(10, digit - 1) + 1; //해당 자리수를 가지고 있는 수에 자리수를 곱해서 더하기
			result += digit * cnt;
			N = (int) Math.pow(10, digit - 1) - 1; //자리 수 낮추기
		}
		System.out.println(result);
		sc.close();
	}
}