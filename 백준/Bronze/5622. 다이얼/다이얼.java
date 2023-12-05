import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String target = sc.next();
		int result = 0;
		for(char c : target.toCharArray()) {
			switch(c) {
				case 'A' : result += 3; break;
				case 'B' : result += 3; break;
				case 'C' : result += 3; break;
				case 'D' : result += 4; break;
				case 'E' : result += 4; break;
				case 'F' : result += 4; break;
				case 'G' : result += 5; break;
				case 'H' : result += 5; break;
				case 'I' : result += 5; break;
				case 'J' : result += 6; break;
				case 'K' : result += 6; break;
				case 'L' : result += 6; break;
				case 'M' : result += 7; break;
				case 'N' : result += 7; break;
				case 'O' : result += 7; break;
				case 'P' : result += 8; break;
				case 'Q' : result += 8; break;
				case 'R' : result += 8; break;
				case 'S' : result += 8; break;
				case 'T' : result += 9; break;
				case 'U' : result += 9; break;
				case 'V' : result += 9; break;
				case 'W' : result += 10; break;
				case 'X' : result += 10; break;
				case 'Y' : result += 10; break;
				case 'Z' : result += 10; break;
			}
		}
		System.out.println(result);
		sc.close();
	}
}