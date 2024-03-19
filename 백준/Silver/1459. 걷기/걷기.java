import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long X = sc.nextLong();
		long Y = sc.nextLong();
		long W = sc.nextLong();
		long S = sc.nextLong();
		long temp1 = (X + Y) * W;
		long temp2 = 0;
		if((X + Y) % 2 == 0) temp2 = Math.max(X,Y) * S;
		else temp2 = (Math.max(X,Y) - 1) * S + W;

	    long temp3 = (Math.min(X, Y)) * S +(Math.abs(X - Y)) * W;

	    System.out.println(Math.min(temp1, Math.min(temp2, temp3)));
		sc.close();
	}
}