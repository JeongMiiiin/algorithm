import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] map = new int[N + 1];
		boolean[] visited = new boolean[N + 1];
		for(int i=1; i <= N; i++) map[i] = sc.nextInt();
		int result = 1;
		int start = sc.nextInt();
		Stack<Integer> stack = new Stack<>();
		visited[start] = true;
		stack.add(start);
		while(!stack.isEmpty()) {
			int cur = stack.pop();
			int dir = map[cur];
			if(cur - dir > 0 && !visited[cur - dir]) {
				result++;
				visited[cur - dir] = true;
				stack.add(cur - dir);
			}
			if(cur + dir <= N && !visited[cur + dir]) {
				result++;
				visited[cur + dir] = true;
				stack.add(cur + dir);
			}
		}
		
		System.out.println(result);
		sc.close();
	}
}