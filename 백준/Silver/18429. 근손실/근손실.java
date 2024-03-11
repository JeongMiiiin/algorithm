import java.util.Scanner;

public class Main {
	static int N, K, result, standard = 500;
	static int[] inputs;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		inputs = new int[N];
		for(int i=0; i < N; i++) inputs[i] = sc.nextInt();
		visited = new boolean[N];
		
		perm(standard, 0);
		
		System.out.println(result);
		sc.close();
	}
	
	private static void perm(int total, int cnt) {
		if(total < standard) return;
		
		if(cnt == N) {
			result++;
			return;
		}
		
		for(int i=0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			perm(total + inputs[i] - K, cnt + 1);
			visited[i] = false;
		}
	}
}