import java.awt.Point;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/*
 * 백준 2583 - 영역 구하기
 * M x N 크기의 모눈종이에서 K개의 직사각형이 존재
 * 직사각형을 제외한 영역들의 개수와 각 분리된 영역의 넓이를 구하시오
 * 
 * */
public class Main {
	static int M, N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		int K = sc.nextInt();
		boolean[][] map = new boolean[M][N];
		
		int startR, startC, endR, endC;
		for(int i=0; i < K; i++) {
			startC = sc.nextInt();
			startR = sc.nextInt();
			endC = sc.nextInt() - 1;
			endR = sc.nextInt() - 1;
			while(startR <= endR) {
				for(int j=startC; j <= endC; j++) map[startR][j] = true;
				
				startR++;
			}
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		Queue<Point> q = new LinkedList<>();
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		Point cur;
		int cnt, nr, nc;
		for(int i=0; i < M; i++) {
			for(int j=0; j < N; j++) {
				if(map[i][j]) continue;
				
				map[i][j] = true;
				cnt = 1;
				q.add(new Point(i, j));
				while(!q.isEmpty()) {
					cur = q.poll();
					for(int d=0; d < 4; d++) {
						nr = cur.x + dr1[d];
						nc = cur.y + dc1[d];
						if(!outMap(nr, nc) && !map[nr][nc]) {
							map[nr][nc] = true;
							cnt++;
							q.add(new Point(nr, nc));
						}
					}
				}
				pq.add(cnt);
			}
		}
		System.out.println(pq.size());
		while(!pq.isEmpty()) System.out.print(pq.poll() + " ");
		
		sc.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= M || c < 0 || c >= N);
	}
}