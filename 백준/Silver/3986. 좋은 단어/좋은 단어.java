import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int result = 0;
		for(int i=0; i < N; i++) {
			Stack<Character> stack = new Stack<>();
			for(char c : sc.nextLine().toCharArray()) {
				if(!stack.isEmpty() && stack.peek() == c) stack.pop();
				else stack.push(c);
			}
			
			if(stack.isEmpty()) result++;
		}
		
		System.out.println(result);
		sc.close();
	}
}