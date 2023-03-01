import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <=T; test_case++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			System.out.println(A + B);
		}
	}
}