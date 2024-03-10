import java.util.Scanner;
import java.util.TreeSet;

public class Main {
	static int N;
	static TreeSet<String> result = new TreeSet<>();
	static int[] inputs, numbers;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); 
		inputs = new int[N];
		numbers = new int[N];
		visited = new boolean[N];
		for(int i=1; i <= N; i++) inputs[i - 1] = i;
		
		perm(0);
		StringBuilder sb = new StringBuilder();
		for(String s : result) sb.append(s + "\n");
		
		System.out.println(sb.toString());
		sc.close();
	}
	
	private static void perm(int cur) {
		if(cur == N) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i < N; i++) sb.append(numbers[i] + " ");
			result.add(sb.toString());
			return;
		}
		
		for(int i=0; i < N; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			numbers[cur] = inputs[i];
			perm(cur + 1);
			visited[i] = false;
		}
	}
}