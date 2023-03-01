import java.util.Scanner;

public class Main {
	private static int N, M;
	private static boolean[] visited;
	private static int[] numbers, inputs;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		visited = new boolean[N];
		numbers = new int[M];
		inputs = new int[N];
		
		for(int i=1; i <= N; i++)
			inputs[i - 1] = i;
		
		perm(0);
		
	}

	private static void perm(int cnt) {
		//기저조건
		if(cnt == M) {
			for(int i=0; i < M; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println();
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
