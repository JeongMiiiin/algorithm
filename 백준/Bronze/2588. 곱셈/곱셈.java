import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int one = y % 10;
		int ten = (y % 100 - one) / 10;
		int hundred = (y % 1000 - (ten + one)) / 100;
		System.out.println(x * one);
		System.out.println(x * ten);
		System.out.println(x * hundred);
		System.out.println(x * y);
		sc.close();
	}
}