import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 2573 빙산
 * 최초의 한 덩어리 빙산이 주어짐.
 * 빙산조각들은 1년이 지날 때마다 인접한 바다 개수 만큼 줄어듬 (0이 될시 사라짐)
 * 두 덩어리 이상으로 분리되는 최초의 시간을 구하라.
 * 단, 전부 다 녹을때까지 두 덩어리 이상으로 분류되지 않으면 0을 출력
 * */

public class Main {
	static int R, C;
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		//맵 세팅
		int[][] map = new int[R][C];
		for(int i=0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j < C; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0, year = 0;
		boolean flag = false;
		while(!flag) {
			++year;
			int[][] tempMap = new int[R][C];
			copyMap(map, tempMap);
			
			for(int i=1; i < R - 1; i++) {
				for(int j=1; j < C - 1; j++) {
					if(tempMap[i][j] > 0) {
						int seaCnt = 0;
						for(int d=0; d < 4; d++) {
							int nr = i + dr1[d];
							int nc = j + dc1[d];
							if(!outMap(nr, nc) && tempMap[nr][nc] == 0) seaCnt++;
						}
						
						map[i][j] = map[i][j] - seaCnt > 0 ? map[i][j] - seaCnt : 0;
					}
				}
			}
			
			int remain = checkMap(map);
			
			if(remain > 1) {
				result = year;
				break;
			} else if(remain == 0) break;
			
		}
		
		
		bw.write(String.valueOf(result));
		bw.close();
		br.close();
		
	}
	
	private static int checkMap(int[][] map) {
		boolean[][] visited = new boolean[R][C];
		
		int cnt = 0;
		Queue<Point> q = new LinkedList<>();
		Point cur;
		for(int i=1; i < R - 1; i++) {
			for(int j=1; j < C - 1; j++) {
				if(map[i][j] > 0 && !visited[i][j]) {
					cnt++;
					q.clear();
					visited[i][j] = true;
					q.add(new Point(i, j));
					while(!q.isEmpty()) {
						cur = q.poll();
						for(int d=0; d < 4; d++) {
							int nr = cur.x + dr1[d];
							int nc = cur.y + dc1[d];
							if(!outMap(nr, nc) && !visited[nr][nc] && map[nr][nc] > 0) {
								visited[nr][nc] = true;
								q.add(new Point(nr, nc));
							}
						}
					}
					
				}
			}
		}
		
		
		return cnt;
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
	
	private static void copyMap(int[][] originMap, int[][] targetMap) {
		for(int i=0; i < originMap.length; i++) targetMap[i] = Arrays.copyOf(originMap[i], originMap[i].length);
	}
}