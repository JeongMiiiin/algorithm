import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 2105 - 디저트 카페
 * 주어지는 값
 * N : 맵의 크기
 * 2번째줄부터 N번째 줄 : 각 칸의 디저트 종류 숫자
 * 
 * 디저트 카페들은 대각선방향으로 움직일 수 있음
 * 대각선 방향으로 움직이고 사각형 모양을 그리며 출발한 카페로 돌아와야 한다
 * 도중 지역을 벗어나면 안됙, 같은 종류의 디저트를 먹어서도 안된다.
 * 갔던 길을 되돌아가서도 안된다.
 * 1. 분할정복으로 큰 대각선부터 확인
*/
public class Solution {
	static int N, ans;
	static int[][] map;
	static boolean[][] visited;
	static boolean[] dessert;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			//초기화
			ans = 0;
			//입력
			N = Integer.parseInt(br.readLine());
			//맵 세팅
			map = new int[N][N];
			StringTokenizer st;
			for(int i=0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i < N - 2; i++) {
				for(int j=1; j< N -1; j++) {
					visited = new boolean[N][N];
					dessert = new boolean[101];
					visited[i][j] = true;
					dessert[map[i][j]] = true;
					goRoute(1, i, j, i, j, 0);
				}
			}
			if(ans == 0) ans--;
			
			//출력
			System.out.println("#" + t + " " + ans);
		}
	}

	static int[] dessertFre;
	//우하, 좌하, 좌상, 우상
	static int[] dr1 = {1,1,-1,-1};
	static int[] dc1 = {1,-1,-1,1};
	private static void goRoute(int cnt, int r, int c, int startR, int startC, int dir) {
		
		int nr, nc;
		for(int d=dir; d < 4; d++) {
			nr = r + dr1[d];
			nc = c + dc1[d];
			if(!outMap(nr, nc)) { //배열을 벗어나지 않은 경우
				if(nr == startR && nc == startC && cnt > 2) {
					ans = Math.max(ans,  cnt);
					return;
				}
				if(!visited[nr][nc] && !dessert[map[nr][nc]]) {
					visited[nr][nc] = true;
					dessert[map[nr][nc]] = true;
					goRoute(cnt + 1, nr, nc, startR, startC, d);
					visited[nr][nc] = false;
					dessert[map[nr][nc]] = false;
				} 
			}
		}
		
	}

	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
}
