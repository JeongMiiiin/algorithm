import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		for(int i=0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			switch(Integer.parseInt(st.nextToken())) {
				case 1 :
					stack.push(Integer.parseInt(st.nextToken()));
					break;
				case 2 : sb.append((stack.isEmpty() ? -1 : stack.pop()) + "\n");
					break;
				case 3 : sb.append(stack.size() + "\n");
					break;
				case 4 : sb.append((stack.isEmpty() ? 1 : 0) + "\n");
					break;
				case 5 : sb.append((stack.isEmpty() ? -1 : stack.peek()) + "\n");
					break;
			}
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}