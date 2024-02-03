import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for(int t=1; t <= T; t++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int result = 1;
			for(int i=0; i < b; i++) result = (result * a) % 10;
			if(result == 0) result = 10;
			sb.append(result + "\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
}