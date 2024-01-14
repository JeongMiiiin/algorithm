import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N + 1];
		for(int i=1; i <= N; i++) arr[i] = sc.nextInt();
		Set<Integer> set = new TreeSet<>();
		for(int i=1; i <= N; i++) {
			boolean[] visited = new boolean[N + 1];
			visited[i] = true;
			Stack<Integer> stack = new Stack<>();
			stack.add(i);
			while(!stack.isEmpty()) {
				int cur = stack.pop();
				if(visited[arr[cur]]) set.add(arr[cur]); //연결이 되어있는 애일 때
				else {
					visited[arr[cur]] = true;
					stack.add(arr[cur]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(set.size() + "\n");
		for(int num : set) sb.append(num + "\n");
		System.out.println(sb.toString());
		sc.close();
	}
}