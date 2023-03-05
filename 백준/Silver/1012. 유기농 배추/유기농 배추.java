import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 1012번 - 유기농 배추
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int M, N, K, visitedCnt;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<= T; t++) {
			//초기화
			visitedCnt = 0;
			
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			for(int i=0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
			}
			
			int ans = 0;
			for(int i=0; i < N; i++) {
				for(int j=0; j < M; j++) {
					if(visitedCnt < K && map[i][j] == 1 && !visited[i][j]) {
						ans++;
						bfs(i, j);
					}
				}
			}
			
			
			bw.write(ans + "\n");
		}
		bw.close();
	}
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	private static void bfs(int y, int x) {
		Queue<Point> q = new ArrayDeque<>();
		
		visited[y][x] = true;
		q.offer(new Point(x,y));
		Point p;
		visitedCnt++;
		int nx, ny;
		while( !q.isEmpty() ) {
			p = q.poll();
			for(int d=0; d < 4; d++) {
				ny = p.y + dr1[d];
				nx = p.x + dc1[d];
				if(outMap(ny, nx)) continue;
				if(map[ny][nx] == 1 && !visited[ny][nx]) {
					visited[ny][nx] = true;
					visitedCnt++;
					q.offer(new Point(nx, ny));
				}
			}
		}
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M);
	}
}