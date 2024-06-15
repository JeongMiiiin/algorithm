import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int result = 0;
		for(int i=1; i * i <= N; i++) result++;
		System.out.println(result);
		sc.close();
	}
}