import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		Stack<Integer> stack = new Stack<Integer>();
		for(int t=0; t < T; t++) {
			int n = sc.nextInt();
			if(n == 0)
				stack.pop();
			else
				stack.push(n);
		}
		
		int sum = 0;
		while( !stack.isEmpty() )
			sum += stack.pop();
		System.out.println(sum);
	}
}
