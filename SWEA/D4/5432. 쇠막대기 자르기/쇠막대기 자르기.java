import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t <= T; t++) {
			int result = 0;
			Stack<Character> stack = new Stack<>();
			boolean flag = false;
			for(char c : sc.nextLine().toCharArray()) {
				if(c == ')') { //닫힐 때
					stack.pop();
					if(flag) {
						result += stack.size();
						flag = false;
					} else result++;
				} else {
					flag = true;
					stack.push(c);
				}
			}
			
			
			sb.append("#" + t + " " + result + "\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}