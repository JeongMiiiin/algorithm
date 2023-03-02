import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 백준 1753 - 최단 경로
 * 주어지는 값
 * V : 정점의 개수
 * E : 간선의 개수
 * 둘째 줄 : 시작 정점의 번호
 * 셋째 줄부터 E개의 줄 : 각 간선을 나타내는 세 개의 정수
 * u : 시작점
 * v : 도착점
 * w : 가중치
 * 모든 정점에는 1부터 숫자가 매겨져 있음
 * 
 * 출력값
 * 각 정점으로 가는 최단 경로를 출력
 * 단, 경로가 존재하지 않는 경우에는 INF로 출력
*/
public class Main {
	static class Vertex implements Comparable<Vertex>{
		int no, cost;
		List<Vertex> linkList;

		public Vertex(int no, int cost) {
			this.no = no;
			this.cost = cost;
			this.linkList = new ArrayList<>(); 
		}

		@Override
		public int compareTo(Vertex o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		
		Vertex[] vList = new Vertex[V + 1];
		
		for(int i=1; i <= V; i++) vList[i] = new Vertex(i, 0);
		
		for(int i=0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			vList[from].linkList.add(new Vertex(to, cost));
		}
		
		final int INF = Integer.MAX_VALUE;
		
		int[] distance = new int[V + 1];
		Arrays.fill(distance, INF);
		
		distance[start] = 0;
		
		boolean[] visited = new boolean[V + 1];
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(vList[start]);
		
		
		while( !pq.isEmpty() ) {
			
			Vertex v = pq.poll();
			
			int dist = v.cost;
			int now = v.no;
			
			if(distance[now] < dist) continue;
			
			for(int i=0; i < vList[now].linkList.size(); i++) {
				int compareCost = distance[now] + vList[now].linkList.get(i).cost;
				int nextNo = vList[now].linkList.get(i).no;
				
				if(compareCost < distance[nextNo]) {
					distance[nextNo] = compareCost;
					pq.offer(new Vertex(nextNo, compareCost));
				}
				
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= V; i++) {
			if(distance[i] != INF) sb.append(distance[i] + "\n");
			else sb.append("INF\n");
		}
		System.out.println(sb.toString());
		
	}
}
