import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 4963 - 섬의 개수
 * 주어지는 값
 * 1. 지도의 너비 - w
 * 2. 지도의 높이 - h
 * 둘째줄부터 h개 줄에는 1은 땅, 0은 바다
 * 입력의 마지막 줄에는 0이 두 개
*/
public class Main {
	static int w, h, ans, orderNum, current;
	static int[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		while(true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w + h == 0) break; //입력의 마지막이 되었을 때는 탈출
			//정답 초기화
			ans = 0;
			orderNum = 2;
			current = 0;
			//맵 만들기
			map = new int[h][w];
			for(int i=0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j < w; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i < h; i++) {
				for(int j=0; j < w; j++) {
					bfs(i, j);
					orderNum++;
				}
			}
			
			System.out.println(ans);
			
		}
	}

	private static void bfs(int r, int c) {
		if(map[r][c] > 1 || map[r][c] == 0) return;
		
		if(current != orderNum) {
			current = orderNum;
			ans++;
		}
		
		map[r][c] = orderNum;
		
		//좌상, 상, 우상, 우, 우하, 하, 좌하, 좌 
		int[] dr1 = {-1,-1,-1,0,1,1,1,0};
		int[] dc1 = {-1,0,1,1,1,0,-1,-1};
		for(int i=0; i < 8; i++) {
			int nr = r + dr1[i];
			int nc = c + dc1[i];
			if(nr < 0 || nr >= h || nc < 0 || nc >= w) continue; //배열을 벗어난 경우
			if(map[nr][nc] == 1) bfs(nr, nc);
		}
	}
}
