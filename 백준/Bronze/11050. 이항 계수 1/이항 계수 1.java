import java.io.IOException;
import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		System.out.println(factorial(N) / (factorial(K) * factorial(N - K)));
		
	}
	
	private static int factorial(int n) {
		if(n == 0) return 1;
		return n * factorial(n - 1);
	}
 
}