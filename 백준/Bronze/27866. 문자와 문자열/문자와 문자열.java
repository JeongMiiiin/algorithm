import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String target = sc.next();
		int targetIdx = sc.nextInt() - 1;
		System.out.println(target.charAt(targetIdx));
		sc.close();
	}
}