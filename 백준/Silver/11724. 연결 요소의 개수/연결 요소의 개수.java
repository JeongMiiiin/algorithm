import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 11724 - 연결 요소의 개수
 * 
 * 주어지는 값
 * N : 정점의 개수
 * M : 간선의 개수
 * 
 * 방향 없는 그래프가 주어졌을 때, 연결 요소의 개수를 구하라
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static class Node {
		int no;
		Node link;
		public Node(int no, Node link) {
			this.no = no;
			this.link = link;
		}
	}
	
	static Node[] nList;
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		nList = new Node[N + 1];
		boolean[] visited = new boolean[N + 1];
		
		
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			nList[from] = new Node(to, nList[from]);
			nList[to] = new Node(from, nList[to]);
		}
		
		int ans = 0;
		for(int i=1; i <= N; i++) {
			if( !visited[i] ) {
				ans++;
				dfs(i, visited);
			}
		}
		
		System.out.println(ans);
	}

	private static void dfs(int current, boolean[] visited) {
		visited[current] = true;
		
		for(Node temp = nList[current]; temp != null; temp = temp.link) {
			if( !visited[temp.no] ) dfs(temp.no, visited);
		}
		
	}
}