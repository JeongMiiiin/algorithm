import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 1240 - 노드 사이의 거리
 * N개의 노드로 이루어진 트리가 주어지고, M개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라
 * 첫째 줄 - 노드의 개수 N, 알고 싶은 노드 쌍의 개수 M
 * N-1개의 줄 - 트리 상에 연결된 두 점과 거리
 * M개 줄 - 거리를 알고 싶은 두 노드 쌍
 * */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 1][N + 1];
		for(int i=0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			map[start][end] = weight;
			map[end][start] = weight;
		}
		
		final int INF = Integer.MAX_VALUE;
		
		StringBuilder sb = new StringBuilder();
		int[] visited = new int[N + 1];
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			Arrays.fill(visited, INF);
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			Queue<Integer> q = new LinkedList<>();
			visited[start] = 0;
			q.add(start);
			while(!q.isEmpty()) {
				int cur = q.poll();
				for(int j=1; j <= N; j++) {
					if(map[cur][j] > 0 && visited[j] > visited[cur] + map[cur][j]) {
						visited[j] = visited[cur] + map[cur][j];
						q.add(j);
					}
				}
			}
			sb.append(visited[end] + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}