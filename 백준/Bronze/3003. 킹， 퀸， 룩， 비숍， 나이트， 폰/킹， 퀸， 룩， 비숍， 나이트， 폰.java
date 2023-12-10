import java.util.Scanner;

public class Main {
	static int[] set = {1, 1, 2, 2, 2, 8};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < 6; i++) sb.append(set[i] - sc.nextInt() + " ");
		System.out.println(sb.toString());
		sc.close();
	}
}