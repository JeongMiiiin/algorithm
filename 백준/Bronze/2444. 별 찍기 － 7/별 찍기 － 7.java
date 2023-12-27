import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		int starIdx = 1;
		for(int i = N - 1; i > 0; i--) {
			for(int j=0; j < i; j++) sb.append(" ");
			for(int j=0; j < starIdx * 2 - 1; j++) sb.append("*");
			starIdx++;
			sb.append("\n");
		}
		for(int i = 0; i < N; i++) {
			for(int j=0; j < i; j++) sb.append(" ");
			for(int j=0; j < starIdx * 2 - 1; j++) sb.append("*");
			starIdx--;
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
		sc.close();
	}
}