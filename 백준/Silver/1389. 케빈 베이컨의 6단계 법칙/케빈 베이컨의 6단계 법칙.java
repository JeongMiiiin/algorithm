import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 1389 - 케빈 베이컨의 6단계 법칙
 * 
 * 주어지는 값
 * N : 유저의 수
 * M : 관계의 수
 * 친구 관계는 중복으로 들어올 수 있으며, 친구가 한명도 없는 사람은 없다.
 * 
 * 케빈 베이컨 6단계 이내에서 서로 아는 사람으로 연결될 수 있음
 * 임의의 두 사람이 최소 몇 단계 만에 이어질 수 있는지 계산
 * 자신을 제외한 유저와 몇단계로 묶이는지 확인하고 총 합이 가장 작은 사람을 출력
 * 단, 가장 작은 사람이 여러명일 경우 번호가 가장 작은 사람 출력
 * 
*/
public class Main {
	static class Node{
		int vertex;
		Node link;
		public Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
		
	}
	
	static int N;
	static int[][] answerList;
	static Node[] nodeList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		answerList = new int[N + 1][N + 1];
		nodeList = new Node[N + 1];
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			nodeList[from] = new Node(to, nodeList[from]);
			nodeList[to] = new Node(from, nodeList[to]);
		}
		
		
		for(int i=1; i <= N; i++) bfs(i);
		
		int cnt = Integer.MAX_VALUE;
		int no = 0;
		for(int i=1; i <= N; i++) {
			int temp = 0;
			for(int j=1; j <= N; j++) temp += answerList[i][j];
			if(cnt > temp) {
				cnt = temp;
				no = i;
			}
		}
		
		System.out.println(no);
	}
	private static void bfs(int cur) {
		boolean[] visited = new boolean[N + 1];
		visited[cur] = true;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(cur);
		int next, cnt = 0;
		while(!q.isEmpty()) {
			cnt++;
			int size = q.size();
			for(int i=0; i < size; i++) {
				next = q.poll();
				for(Node temp = nodeList[next]; temp != null; temp = temp.link) {
					if( !visited[temp.vertex] ) {
						answerList[cur][temp.vertex] = cnt;
						visited[temp.vertex] = true;
						q.offer(temp.vertex);
					}
				}
			}
		}
		
		
	}
}