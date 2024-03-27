import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		Stack<Integer> stack = new Stack<>();
		for(char c : s.toCharArray()) {
			if(c == ')') { //괄호를 닫아야 할 때
				int temp = 0;
				while(stack.peek() != 0) temp += stack.pop();
				stack.pop();
				stack.push(temp);
			}
			else if(c == 'C') stack.push(12);
			else if(c == 'H') stack.push(1);
			else if(c == 'O') stack.push(16);
			else if(c >= '0' && c <= '9') stack.push(stack.pop() * (c - '0'));
			else stack.push(0);
		}
		int result = 0;
		while(!stack.isEmpty()) result += stack.pop();
		
		System.out.println(result);
		sc.close();
	}
}