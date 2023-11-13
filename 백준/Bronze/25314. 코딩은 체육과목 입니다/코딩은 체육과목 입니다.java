import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		int share = N / 4;
		for(int i=0; i < share; i++) sb.append("long ");
		
		sb.append("int");
		System.out.println(sb.toString());
		sc.close();
	}
}