import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] cntList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		//맵 세팅
		map = new int[N][N];
		int maxCnt = 0;
		StringTokenizer st;
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxCnt = Math.max(maxCnt, map[i][j]);
			}
		}
		visited = new boolean[N][N];
		cntList = new int[maxCnt];
		for(int i=0; i < maxCnt; i++) {
			find(i);
			for(int j=0; j < N; j++) Arrays.fill(visited[j], false);
		}
		
		int result = 0;
		for(int i=0; i < maxCnt; i++) result = Math.max(result, cntList[i]);
		
		bw.write(String.valueOf(result));
		
		bw.close();
		br.close();
	}
	
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	
	private static void find(int curIdx) {
		Queue<Point> q = new LinkedList<>();
		int cnt = 0;
		for(int i=0; i < N; i++) {
			for(int j=0; j < N; j++) {
				if(map[i][j] > curIdx && !visited[i][j]) { //물에 잠기지 않는 영역이면서 방문하지 않은 곳일 때
					 cnt++; //값 올리기
					 visited[i][j] = true;
					 q.add(new Point(i, j));
					 Point cur;
					 while(!q.isEmpty()) {
						 cur = q.poll();
						 for(int d=0; d < 4; d++) {
							 int nr = cur.x + dr1[d];
							 int nc = cur.y + dc1[d];
							 if(!outMap(nr, nc) && !visited[nr][nc] && map[nr][nc] > curIdx) {
								 visited[nr][nc] = true;
								 q.add(new Point(nr, nc));
							 }
						 }
					 }
				}
			}
		}
		
		cntList[curIdx] = cnt;
		
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
	
}