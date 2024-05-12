import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		if(N % 2 == 0) System.out.println("SK");
		else System.out.println("CY");
		
		sc.close();
	}
}