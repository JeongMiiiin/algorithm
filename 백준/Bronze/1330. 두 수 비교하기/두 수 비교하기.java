import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		String result = "==";
		if(A < B) result = "<";
		if(A > B) result = ">";
		System.out.println(result);
	}
}
