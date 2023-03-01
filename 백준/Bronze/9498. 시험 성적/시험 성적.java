import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int score = sc.nextInt();
		
		String rank = "F";
		
		if(score > 89) rank = "A";
		else if(score > 79) rank = "B";
		else if(score > 69) rank = "C";
		else if(score > 59) rank = "D";
		
		System.out.println(rank);
	}
}