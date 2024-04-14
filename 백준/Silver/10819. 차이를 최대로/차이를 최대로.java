import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int[] arr, numbers;
	static boolean[] visited;
	static int result = 0, N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = Integer.parseInt(sc.nextLine());
		arr = new int[N];
		numbers = new int[N];
		visited = new boolean[N];
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		for(int i=0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		find(0);
		System.out.println(result);
		sc.close();
	}
	
	private static void find(int cnt) {
		if(cnt == N) {
			int temp = 0;
			for(int i=1; i < N; i++) temp += Math.abs(numbers[i - 1] - numbers[i]);
			result = Math.max(result, temp);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			numbers[cnt] = arr[i];
			find(cnt + 1);
			visited[i] = false;
		}
	}
}