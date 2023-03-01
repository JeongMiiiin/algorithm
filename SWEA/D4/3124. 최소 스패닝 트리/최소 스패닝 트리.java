import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * SWEA 3124 - 최소 스패닝 트리
 * 주어지는 값
 * T : 테스트 케이스 수
 * 첫째 줄 -> V : 정점의 개수, E : 간선의 개수
 * 다음 E개의 줄 : 각 간선에 대한 정보 A : from B : to C : weight
 * C는 음수일 수 있다.
 * 모든 정점들을 연결하는 부분 그래프중에서 그 가중치의 합이 최소인 트리
 * PRIMS Algorithm 사용
 * 1. 각 정점간의 비용을 관리하기 위한 2차원 int 배열 data[][] 생성
 * 2. 정점이 포함되었는지를 판단하기 위한  1차원 boolean 배열 visited[] 생성
 * 3. 간선간의 데이터를 통해 data[][] 값 채우기
 * 4. PRIMS Algo 실행하여 최소값 찾기
*/
public class Solution {
	static class Edge {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
	}
	
	static class Vertex implements Comparable<Vertex>{
		int no, cost;
		public Vertex(int no, int cost) {
			this.no = no;
			this.cost = cost;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.cost, o.cost);
			//return this.cost - o.cost;
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());
			List[] adjList = new ArrayList[V + 1];
			for(int i=1; i <= V; i++) adjList[i] = new ArrayList<Edge>();
			boolean[] visited = new boolean[V + 1];
			
			int E = Integer.parseInt(st.nextToken());
			
			for(int i=0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				adjList[from].add(new Edge(from, to, weight));
				adjList[to].add(new Edge(to, from, weight));
			}
			
			int[] minCost = new int[V + 1];
			
			Arrays.fill(minCost, Integer.MAX_VALUE);
			
			minCost[1] = 0;
			long ans = 0;
			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			pq.offer(new Vertex(1, 0));
			int cnt = 0;
			
			while( !pq.isEmpty() ) {
				Vertex minVertex = pq.poll();
				if(visited[minVertex.no]) continue; //이미 방문한 정점인 경우
				
				//이제 내편으로 (최소신장트리에 포함되었다)
				visited[minVertex.no] = true;
				ans += minVertex.cost;
				
				if(++cnt == V) break;
				
				for(Edge e : (List<Edge>) adjList[minVertex.no]) {
					if(!visited[e.to] && e.weight != 0
							&& minCost[e.to] > e.weight) {
						minCost[e.to] = e.weight;
						pq.offer(new Vertex(e.to, e.weight));
					}
				}
			}
			
			//출력
			bw.write("#" + t + " " + ans + "\n");
		}
		
		bw.close();
		
	}
	
}
