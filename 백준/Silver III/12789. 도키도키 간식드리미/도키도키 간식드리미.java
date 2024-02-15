import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Stack<Integer> stack = new Stack<>();
		boolean flag = true;
		int idx = 1;
		for(int i=0; i < N; i++) {
			int cur = sc.nextInt();
			if(cur == idx) {
				idx++; //지나가야 하는 번호인 경우
				while(!stack.isEmpty() && idx == stack.peek()) { //현재 스택에 있는 값이 나와야 할 때
					idx++;
					stack.pop();
				}
			} 
			else stack.add(cur);
		}
		
		while(!stack.isEmpty()) {
			if(idx++ != stack.pop()) {
				flag = false;
				break;
			}
		}
		
		System.out.println(flag ? "Nice" : "Sad");
		sc.close();
	}
}