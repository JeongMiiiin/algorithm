import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int result = 0;
		Stack<Character> stack = new Stack<>();
		boolean flag = false;
		for(char c : sc.nextLine().toCharArray()) {
			if(c == '(') {
				stack.push(c);
				flag = true;
			} else {
				stack.pop();
				if(flag) result += stack.size();
				else result++;
				flag = false;
			}
		}
		System.out.println(result);
		sc.close();
	}
}