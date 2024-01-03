import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m, cnt, result; // 행, 열, 컴포넌트 수
	static int[] parent; // 부모 배열
	//상 우 하 좌
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] graph, component, fungus; // 그래프, 컴포넌트 집합군, 곰팡이의 자라는 속도
	
	public static void main(String[] args) throws IOException {
		result = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st  = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n][m];
		fungus = new int[n][m];
		component = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			int j = 0;
			for (char c : br.readLine().toCharArray()) graph[i][j++] = c - '0';
		}
		
		// 연결 컴포넌트의 개수를 찾음
		cnt = 0;
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (graph[i][j] != 0) {
					if (component[i][j] == 0) findComponent(i, j, ++cnt);
					queue.add(new int[] {i, j, graph[i][j], component[i][j]});
				}
			}
		}
		
		// 컴포넌트의 수 크기만큼 부모 배열 선언
		parent = new int[cnt + 1];
		for (int i = 0; i < cnt + 1; i++) parent[i] = i;
		
		go(queue);
		
		bw.write(result + "");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int find(int x) {
		if (parent[x] != x) parent[x] = find(parent[x]);
		return parent[x];
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}
	
	static void findComponent(int r, int c, int area) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r, c});
		component[r][c] = area;
		while (!queue.isEmpty()) {
			int[] pair = queue.poll();
			for (int k = 0; k < 4; k++) {
				int nr = pair[0] + dr[k];
				int nc = pair[1] + dc[k];
				if (outMap(nr, nc)) continue;
				if (graph[nr][nc] != 0 && component[nr][nc] == 0) {
					component[nr][nc] = area;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	static void go(Queue<int[]> queue) {
		if (cnt == 1) return;
		result++;
		
		Queue<int[]> nextQueue = new LinkedList<>();
		int size = queue.size();
		int r, c, k;
		for (int i = 0; i < size; i++) {
			int[] pair = queue.poll();
			r = pair[0];
			c = pair[1];
			k = pair[2];
			// 주변 2 * k + 1 크기의 격자판으로 퍼짐
			for (int nr = r - k; nr < r + k + 1; nr++) {
				for (int nc = c - k; nc < c + k + 1; nc++) {
					if (outMap(nr, nc)) continue;
					if (graph[nr][nc] == 0) { // 빈 칸이면 해당 곰팡이가 퍼짐
						graph[nr][nc] = k;
						component[nr][nc] = pair[3];
						nextQueue.add(new int[] {nr, nc, k, pair[3]});
					} else if (graph[nr][nc] <= k) { // 빈 칸이 아닌데, 해당 곰팡이의 퍼지는 속도가 작고
						if (find(component[nr][nc]) != find(pair[3])) { // 같은 종이 아니면 퍼짐
							component[nr][nc] = pair[3];
							graph[nr][nc] = k;
							nextQueue.add(new int[] {nr, nc, k, pair[3]});
						}
					}
				}
			}
		}
		
		while (!nextQueue.isEmpty()) {
			int[] pair = nextQueue.poll();
			r = pair[0];
			c = pair[1];
			k = pair[2];
			if (k < graph[r][c]) continue;
			// 인접한 주변이 곰팡이면 연결
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (outMap(nr, nc)) continue;
				if (graph[nr][nc] != 0) {
					if (find(component[nr][nc]) != find(pair[3])) {
						union(component[nr][nc], pair[3]);
						cnt -= 1; // 집합을 1개 감소
					}
				}
			}
			queue.add(pair);
		}
		go(queue);
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= n || c < 0 || c >= m);
	}
}