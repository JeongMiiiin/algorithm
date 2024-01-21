import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int result = 0;
		char prev = '2';
		for(char c : sc.next().toCharArray()) {
			if(prev != c) {
				result++;
				prev = c;
			}
		}
		System.out.println(result / 2);
		sc.close();
	}
}