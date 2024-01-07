import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 14940 - 쉬운 최단거리
 * 모든 지점에 대해서 목표지점까지의 거리를 구하여라
 * */
public class Main {
	static int R, C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];
		Queue<Point> q = new LinkedList<>();
		int[][] result = new int[R][C];
		boolean[][] visited = new boolean[R][C];
		for(int i=0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					visited[i][j] = true;
					q.add(new Point(i, j));
				}
			}
		}
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
				
		//목표지점에서 출발
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i < size; i++) {
				Point cur = q.poll();
				result[cur.x][cur.y] = cnt;
				for(int d=0; d < 4; d++) {
					int nr = cur.x + dr1[d];
					int nc = cur.y + dc1[d];
					if(outMap(nr, nc) || map[nr][nc] == 0 || visited[nr][nc]) continue;
					visited[nr][nc] = true;
					q.add(new Point(nr, nc));
				}
			}
			cnt++;
		}
		
		//못가는 곳 찾기
		for(int i=0; i < R; i++) {
			for(int j=0; j < C; j++) {
				if(map[i][j] != 1 || visited[i][j]) continue;
				result[i][j] = -1;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < R; i++) sb.append(Arrays.toString(result[i]).replace("[", "").replace("]", "").replaceAll(",", "") + "\n");
		System.out.println(sb.toString());
		br.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
}