import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int y = sc.nextInt();
		
		int result = 0;
		if(y % 4 == 0)
			if(y % 100 != 0 || y % 400 == 0) result = 1;
		
		System.out.println(result);
	}
}
