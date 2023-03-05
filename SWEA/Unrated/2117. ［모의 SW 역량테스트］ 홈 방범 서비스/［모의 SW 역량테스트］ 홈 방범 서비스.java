import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * SWEA 2117 - 홈 방범 서비스
 * 주어지는 값
 * T : 테스트 케이스 수
 * N : 도시의 크기
 * M : 하나의 집이 지불할 수 있는 비용
 * 
 * 마름모영역으로 홈 방범 서비스를 제공한다
 * 서비스 영역의 크기 K가 커질수록 운영비용도 커진다
 * 운영비용 : K * K + (K - 1) * (K - 1)
 * 하나의 집마다 얻을 수 있는 비용 M
 * 손해를 보지 않으면서 홈 방범 서비스를 가장 많은 집들에 제공하는 서비스 영역을 찾고,
 * 그때의 집들의 수를 출력
*/
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int N, M, ans, homeCnt;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			//초기화
			ans = -1;
			homeCnt = 0;
			//입력
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for(int i=0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) homeCnt++;
				}
			}
			
			for(int k = N + 1; k > 0; k--) {
				if(homeCnt * M >= k * k + (k - 1)*(k - 1)) for(int i=0; i < N; i++) for(int j=0; j < N; j++) bfs(k, i, j);
			}
			
			//출력
			bw.write("#" + t + " " + ans + "\n");
		}
		
		bw.close();
	}
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
 	private static void bfs(int k, int r, int c) {
 		boolean[][] visited = new boolean[N][N];
		Queue<Point> q = new ArrayDeque<>();
		visited[r][c] = true;
		q.offer(new Point(r, c));
 		
		int nr, nc, cnt = 0;
		if(map[r][c] == 1) cnt++;
		Point p;
		while(!q.isEmpty()) {
			p = q.poll();
			for(int d=0; d < 4; d++) {
				nr = p.x + dr1[d];
				nc = p.y + dc1[d];
				if(outMap(nr,nc)) continue; //배열을 벗어난 경우
				if(distanceOutMap(k, r, c, nr, nc)) continue; //거리가 벗어난 경우
				if(visited[nr][nc]) continue; //들렀던 곳일 경우
				if(map[nr][nc] == 1) cnt++;
				visited[nr][nc] = true;
				q.offer(new Point(nr, nc));
			}
		}
		
		if(ans > cnt) return;
		
		int resultCost = (cnt * M) - (k * k + (k - 1)*(k - 1));
		if(resultCost >= 0) ans = cnt;
	}
 	
 	private static boolean outMap(int r, int c) {
 		return (r < 0 || r >= N || c < 0 || c >= N);
 	}
 	
 	private static boolean distanceOutMap(int k, int r, int c, int nr, int nc) {
 		return (Math.abs(nr - r) + Math.abs(nc - c) > k - 1);
 	}
}
