import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int result = 0;
		for(int i=0; i < 8; i++) {
			String s = sc.nextLine();
			int j=0;
			for(char c : s.toCharArray()) {
				if(c == 'F' && (i + j) % 2 == 0) result++;
				j++;
			}
		}
		System.out.println(result);
		sc.close();
	}
}