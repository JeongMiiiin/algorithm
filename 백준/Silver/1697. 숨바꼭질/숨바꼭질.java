import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/*
 * 백준 1697 - 숨바꼭질
 * 수빈이는 현재 N에 위치
 * 동생은 K에 위치
 * 수빈이는 걷거나 순간이동 가능
 * 걸으면 현재 위치에서 +-1
 * 순간이동인 경우 현재위치 * 2
 * 
 * 가장 빠른시간은 몇초인가
*/
public class Main {
	static int N, K;
	static int[] visited = new int[100001];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		bfs();
		
		System.out.println(visited[K]);
		sc.close();
	}
	private static void bfs() {
		 Queue<Integer> q= new ArrayDeque<>();
		 q.offer(N);
		 
		 while( !q.isEmpty() ) {
			 int current = q.poll();
			 if(current == K) break;
			 
			 for(int i=0; i < 3; i++) {
				 int next = current + 1;
				 
				 if(i == 1) next = current - 1;
				 else if(i == 2) next = current * 2;
				 
				 if(next >=0 && next <= 100000 && visited[next] == 0) {
					 q.offer(next);
					 visited[next] = visited[current] + 1;
				 }
			 }
		 }
		
	}
}
