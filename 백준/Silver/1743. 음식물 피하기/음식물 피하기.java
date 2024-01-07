import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 1743 - 음식물 피하기
 * 떨어진 음식물 중에 제일 큰 음식물만은 피해 가려고 한다.
 * 제일 큰 음식물의 크기를 출력하라
 * */
public class Main {
	static int R, C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];
		for(int i=0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
		}
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		int result = 0;
		boolean[][] visited = new boolean[R][C];
		for(int i=0; i < R; i++) {
			for(int j=0; j < C; j++) {
				if(map[i][j] == 0 || visited[i][j]) continue; //빈 곳이거나 이미 방문한 곳이면 패스
				int temp = 0;
				Queue<Point> q = new LinkedList<>();
				visited[i][j] = true;
				q.add(new Point(i, j));
				Point cur;
				while(!q.isEmpty()) {
					temp++;
					cur = q.poll();
					for(int d=0; d < 4; d++) {
						int nr = cur.x + dr1[d];
						int nc = cur.y + dc1[d];
						if(outMap(nr, nc) || map[nr][nc] == 0 || visited[nr][nc]) continue;
						visited[nr][nc] = true;
						q.add(new Point(nr, nc));
					}
				}
				result = Math.max(result, temp);
			}
		}
		
		System.out.println(result);
		br.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
}