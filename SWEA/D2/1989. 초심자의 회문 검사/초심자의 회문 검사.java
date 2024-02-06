import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(sc.nextLine());
		for(int t=1; t <= T; t++) {
			int result = 1;
			String s = sc.nextLine();
			for(int i=0; i <= s.length() / 2; i++) {
				if(s.charAt(i) != s.charAt(s.length() - 1 - i)) {
					result = 0;
					break;
				}
			}
			
			sb.append("#" + t + " " + result + "\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}