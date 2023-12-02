import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1; t <= T; t++) {
			String s = sc.next();
			System.out.println(s.charAt(0) + "" + s.charAt(s.length() - 1));
		}
		sc.close();
	}
}