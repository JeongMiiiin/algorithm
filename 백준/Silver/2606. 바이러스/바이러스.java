import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/*
 * 백준 2606 - 바이러스
 * 주어지는 값
 * N : 정점의 개수
 * M : 간선의 개수
 * 3번쨰 줄부터 M번째 줄 : 연결되어있는 컴퓨터 관계
 * 
 * 1번 컴퓨터가 감염되었을 때 바이러스에 감염되는 컴퓨터의 수 출력
*/
public class Main {
	static class Vertex {
		Vertex[] linkList;

		public Vertex(Vertex[] linkList) {
			this.linkList = linkList;
		}
		
	}
	
	static boolean[] visited;
	static Vertex[] computers;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		visited = new boolean[N + 1];
		computers = new Vertex[N + 1];
		for(int i=1; i <= N; i++) computers[i] = new Vertex(new Vertex[N + 1]);
		
		int M = sc.nextInt(), from, to;
		for(int i=0; i < M; i++) {
			from = sc.nextInt();
			to = sc.nextInt();
			computers[from].linkList[to] = computers[to];
			computers[to].linkList[from] = computers[from];
		}
		
		Queue<Vertex> q = new ArrayDeque<>();
		visited[1] = true;
		q.offer(computers[1]);
		int ans = 0;
		Vertex v;
		while(!q.isEmpty()) {
			v = q.poll();
			for(int i=1; i < v.linkList.length; i++) {
				if(v.linkList[i] != null && !visited[i]) {
					visited[i] = true;
					q.offer(computers[i]);
					ans++;
				}
			}
		}
		
		
		System.out.println(ans);
		sc.close();
	}
}
