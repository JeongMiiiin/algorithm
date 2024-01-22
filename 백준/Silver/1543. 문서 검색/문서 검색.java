import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String find = sc.nextLine();
		int result = 0, idx = 0;
		while(idx < s.length()) {
			int findIdx = 0;
			while(findIdx < find.length() && idx < s.length() && s.charAt(idx) == find.charAt(findIdx)) {
				idx++;
				findIdx++;
			}
			if(findIdx == find.length()) result++;
			else idx -= findIdx - 1;
		}
		
		System.out.println(result);
		sc.close();
	}
}