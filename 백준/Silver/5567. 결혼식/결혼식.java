import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		int m = Integer.parseInt(sc.nextLine());
		boolean[][] map = new boolean[n + 1][n + 1];
		for(int i=0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = true;
			map[b][a] = true;
		}
		boolean[] visited = new boolean[n + 1];
		int result = 0;
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visited[1] = true;
		int dir = 0;
		while(dir++ < 2 && !q.isEmpty()) {
			int size = q.size();
			for(int i=0; i < size; i++) {
				int cur = q.poll();
				for(int j=2; j <= n; j++) {
					if(map[cur][j] && !visited[j]) {
						result++;
						visited[j] = true;
						q.add(j);
					}
				}
			}
			
		}
		
		System.out.println(result);
		sc.close();
	}
}