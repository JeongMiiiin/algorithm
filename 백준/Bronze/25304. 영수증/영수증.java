import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		int N = sc.nextInt();
		int total = 0;
		for(int i=0; i < N; i++) {
			int payment = sc.nextInt();
			int amount = sc.nextInt();
			total += payment * amount;
		}
		
		if(total == X) System.out.println("Yes");
		else System.out.println("No");
		
		sc.close();
	}
}