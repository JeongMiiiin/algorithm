import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int cnt = 0;
		while(s.length() > 1) {
			cnt++;
			long temp = 0;
			for(char c : s.toCharArray()) temp += c - '0';
			s = Long.toString(temp);
		}
		
		System.out.println(cnt);
		System.out.println(Integer.parseInt(s) % 3 == 0 ? "YES" : "NO");
		sc.close();
	}
}