import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/*
 * 백준 11403 - 경로 찾기
 * 
 * 주어지는 값
 * N : 정점의 개수
 * 둘째줄 부터 N개 줄 : 그래프의 인접 행렬
 * 
 * 가중치 없는 방향그래프 G가 주어졌을 때
 * 모든 정점 (i, j)에 대해서 i에서 j로 가능 경로가 있는지 없는지 구하라
 * 인접행렬로 출력
 * 
*/
public class Main {
	
	static int N;
	static int[][] adjMatrix;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		adjMatrix = new int[N][N];
		
		//인접행렬 만들기
		for(int i=0; i < N; i++) for(int j=0; j < N; j++) adjMatrix[i][j] = sc.nextInt();
		
		for(int i=0; i < N; i++) bfs(i);
		
		for(int i=0; i < N; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=0; j < N; j++) sb.append(adjMatrix[i][j] + " ");
			System.out.println(sb.toString());
		}
		
		sc.close();
	}
	
	private static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N];
		q.offer(start);
		
		int cur;
		while( !q.isEmpty() ) {
			cur = q.poll();
			for(int i=0; i < N; i++) {
				if(adjMatrix[cur][i] != 0 && !visited[i]) {
					adjMatrix[start][i] = 1;
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		
		
	}
}