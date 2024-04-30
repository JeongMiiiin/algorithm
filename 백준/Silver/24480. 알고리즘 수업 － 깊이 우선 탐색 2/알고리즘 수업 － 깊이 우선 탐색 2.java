import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		List<Integer>[] map = new ArrayList[N + 1];
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(sc.nextLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(map[a] == null) map[a] = new ArrayList<Integer>();
			if(map[b] == null) map[b] = new ArrayList<Integer>();
			map[a].add(b);
			map[b].add(a);
		}
		Stack<Integer> stack = new Stack<>();
		int[] result = new int[N + 1];
		int idx = 1;
		stack.push(R);
		while(!stack.isEmpty()) {
			int cur = stack.pop();
			if(result[cur] == 0) result[cur] = idx++;
			if(map[cur] != null) {
				Collections.sort(map[cur]);
				for(int next : map[cur]) if(result[next] == 0) stack.push(next);
			}
			
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1; i <= N; i++) sb.append(result[i] + "\n");
		
		System.out.println(sb.toString());
		sc.close();
	}
}