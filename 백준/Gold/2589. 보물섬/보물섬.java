import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 2589 - 보물섬
 * 
 * */

public class Main {
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//세팅
		char[][] map = new char[N][M];
		for(int i=0; i < N; i++) {
			int j = 0;
			for(char c : br.readLine().toCharArray()) map[i][j++] = c;
		}
		
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		
		int result = 0;
		boolean[][] visited = new boolean[N][M]; //방문처리할 배열
		Queue<Point> q = new LinkedList<>();
		for(int i=0; i < N; i++) {
			for(int j=0; j < M; j++) {
				if(map[i][j] == 'W') continue;
				resetVisited(visited);
				visited[i][j] = true;
				q.add(new Point(i, j));
				int temp = -1, nr , nc;
				Point cur;
				while(!q.isEmpty()) {
					int size = q.size();
					for(int z=0; z < size; z++) {
						cur = q.poll();
						for(int d=0; d < 4; d++) {
							nr = cur.x + dr1[d];
							nc = cur.y + dc1[d];
							if(!outMap(nr, nc) && !visited[nr][nc] && map[nr][nc] == 'L') {
								visited[nr][nc] = true;
								q.add(new Point(nr, nc));
							}
						}
					}
					temp++;
				}
				result = Math.max(result, temp);
			}
		}
		
		
		System.out.println(result);
		br.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M);
	}
	
	private static void resetVisited(boolean[][] visited) {
		for(int i=0; i < visited.length; i++) Arrays.fill(visited[i], false);
	}
}