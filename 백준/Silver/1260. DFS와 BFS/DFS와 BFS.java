import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 1260번 - DFS와 BFS
*/
public class Main {
	static int N, M, V;
	static List<Integer>[] adjList;
	static boolean[] dfsSelected;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder dfsSb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N + 1];
		dfsSelected = new boolean[N + 1];
		for(int i=1; i <= N; i++) adjList[i] = new ArrayList<Integer>();
		
		int from, to;
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}
		
		dfs(V);
		System.out.println(dfsSb.toString());
		bfs(V);
	}

	private static void dfs(int start) {
		Collections.sort(adjList[start]);
		dfsSb.append(start + " ");
		dfsSelected[start] = true;
		
		for(int vertex : adjList[start]) {
			if( !dfsSelected[vertex] ) {
				dfsSelected[vertex] = true;
				dfs(vertex);
			}
			
		}
		
	}

	private static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		q.offer(start);
		visited[start] = true;
		
		Collections.sort(adjList[start]);
		
		StringBuilder sb = new StringBuilder();
		int current = 0;
		while( !q.isEmpty() ) {
			current = q.poll();
			sb.append(current + " ");
			
			for(int vertex : adjList[current]) {
				if( !visited[vertex]) {
					q.offer(vertex);
					visited[vertex] = true;
				}
			}
		}
		System.out.println(sb.toString());
		
	}
}
