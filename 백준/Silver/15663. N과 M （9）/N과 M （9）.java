import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main {
	static int N, M;
	static LinkedHashSet<String> ans = new LinkedHashSet<>();
	static int[] inputs, numbers;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		inputs = new int[N];
		visited = new boolean[N];
		numbers = new int[M];
		for(int i=0; i < N; i++) inputs[i] = sc.nextInt();
		Arrays.sort(inputs);
		
		perm(0);
		ans.forEach(System.out::println);
		sc.close();
	}
	private static void perm(int cnt) {
		if(cnt == M) {
			String s = "";
			for(int i=0; i < M;i++)
				s += numbers[i] + " ";
			
			ans.add(s);
			return;
		}
		
		for(int i=0; i < N; i++) {
			if(visited[i]) continue;
			
			numbers[cnt] = inputs[i];
			visited[i] = true;
			perm(cnt + 1);
			visited[i] = false;
		}
	}
}
