import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[T + 1][T + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		int prev, next;
		int m = Integer.parseInt(br.readLine());
		for(int i=0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			prev = Integer.parseInt(st.nextToken());
			next = Integer.parseInt(st.nextToken());
			map[prev][next] = true;
			map[next][prev] = true;
		}
		boolean[] visited = new boolean[T + 1];
		int result = - 1, cnt = 0;
		Queue<Integer> q = new LinkedList<>();
		q.add(x);
		visited[x] = true;
		int cur;
		outer : while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i < size; i++) {
				cur = q.poll();
				if(cur == y) {
					result = cnt;
					break outer;
				}
				for(int j=1; j <= T; j++) {
					if(map[cur][j] && !visited[j]) {
						visited[j] = true;
						q.add(j);
					}
				}
			}
			
			cnt++;
		}
		
		bw.write(String.valueOf(result));
		bw.close();
		br.close();
	}
}