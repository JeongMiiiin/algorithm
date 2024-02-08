import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static Set<String> set;
	static int N, K;
	static int[] arr, numbers;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		set = new HashSet<>();
		N = sc.nextInt();
		K = sc.nextInt();
		arr = new int[N];
		numbers = new int[K];
		for(int i=0; i < N; i++) arr[i] = sc.nextInt();
		visited = new boolean[N];
		
		perm(0);
		
		System.out.println(set.size());
		sc.close();
	}
	
	private static void perm(int cnt) {
		if(cnt == K) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i < K; i++) sb.append(numbers[i]);
			set.add(sb.toString());
			return;
		}
		
		for(int i=0; i < N; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			numbers[cnt] = arr[i];
			perm(cnt + 1);
			visited[i] = false;
		}
	}
}