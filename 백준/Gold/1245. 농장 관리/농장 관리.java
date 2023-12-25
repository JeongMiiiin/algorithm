import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//좌상 상 우상 우 우하 하 좌하 좌
		int[] dr1 = {-1, -1, -1, 0, 1, 1, 1, 0};
		int[] dc1 = {-1, 0, 1, 1, 1, 0, -1, -1};
		
		Queue<Point> q = new LinkedList<>();
		
		boolean[][] visited = new boolean[N][M];
		int result = 0;
		for(int i=0; i < N; i++) {
			for(int j=0; j < M; j++) {
				if(visited[i][j] || map[i][j] == 0) continue; //이미 봉우리에 포함된 곳이거나 땅인 경우 패스
				
				visited[i][j] = true;
				int temp = map[i][j];
				boolean flag = true;
				q.clear();
				q.add(new Point(i, j));
				while(!q.isEmpty()) {
					Point cur = q.poll();
					for(int d=0; d < 8; d++) {
						int nr = cur.x + dr1[d];
						int nc = cur.y + dc1[d];
						if(outMap(nr, nc)) continue; //벗어났을 때
						
						if(map[nr][nc] > temp) flag = false;
						else if(map[nr][nc] == temp && !visited[nr][nc]) {
							visited[nr][nc] = true;
							q.add(new Point(nr, nc));
						}
					}
				}
				if(flag) result++;
			}
		}
		System.out.println(result);
		br.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M);
	}
}