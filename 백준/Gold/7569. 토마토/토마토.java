import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 7569 - 토마토
 * 주어지는 값
 * M : 상자의 가로 크기
 * N : 상자의 세로 크기
 * H : 상자의 높이
 * 둘째줄부터는 저장된 상자의 정보
 * 정보 종류
 * 1 : 익은 토마토
 * 0 : 안 익은 토마토
 * -1 : 비어있는 곳
 * 익은 토마토는 상하좌우로 영향을 줘서 1일이 지나면 익게 됨
 * 상자의 토마토가 모두 익을때까지의 최소 날짜 단, 모두 익지 못하면 -1 출력
 * 
*/
public class Main {
	static class Tomato {
		int h, r, c;

		public Tomato(int h, int r, int c) {
			this.h = h;
			this.r = r;
			this.c = c;
		}
	}
	static int M, N, H, ans;
	static int[][][] map;
	static Queue<Tomato> unRipe = new ArrayDeque<>();
	static Queue<Tomato> ripe = new ArrayDeque<>();
	static int ripeCnt, unRipeCnt, emptyCnt;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//입력
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][N][M];
		for(int i=0; i < H; i++) {
			for(int j=0; j < N; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int k=0; k < M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if(map[i][j][k] == 1) ripe.offer(new Tomato(i, j, k));
					else if(map[i][j][k] == 0) unRipe.offer(new Tomato(i, j, k));
				}
			}
		}
		
		if(unRipe.size() == 0) {
			System.out.println(0);
			return;
		} else if(ripe.size() == 0) {
			System.out.println(-1);
			return;
		}
		
		unRipeCnt = unRipe.size();
		
		if( !possibleRipe() ) ans = -1;
		else calcRipe();	
		
		System.out.println(ans);
		
	}

	private static void calcRipe() {
		boolean[][][] visited = new boolean[H][N][M];
		int days = 1;
		
		outer : while(!ripe.isEmpty()) {
			int size = ripe.size();
			for(int i=0; i < size; i++) {
				Tomato t = ripe.poll();
				visited[t.h][t.r][t.c] = true;
				int nr, nc;
				for(int d=0; d < 4; d++) {
					nr = t.r + dr1[d];
					nc = t.c + dc1[d];
					if(outToMap(nr,nc)) continue;
					if(!visited[t.h][nr][nc] && map[t.h][nr][nc] == 0) {
						visited[t.h][nr][nc] = true;
						map[t.h][nr][nc] = 1;
						unRipeCnt--;
						if(unRipeCnt == 0) break outer;
						else ripe.offer(new Tomato(t.h,nr,nc));
					}
				}
				
				//위 확인
				if(t.h - 1 > -1 && !visited[t.h - 1][t.r][t.c] && map[t.h - 1][t.r][t.c] == 0) {
					visited[t.h - 1][t.r][t.c] = true;
					map[t.h - 1][t.r][t.c] = 1;
					unRipeCnt--;
					if(unRipeCnt == 0) break outer;
					else ripe.offer(new Tomato(t.h - 1,t.r,t.c));
				}
				
				//아래 확인
				if(t.h + 1 < H && !visited[t.h + 1][t.r][t.c] && map[t.h + 1][t.r][t.c] == 0) {
					visited[t.h + 1][t.r][t.c] = true;
					map[t.h + 1][t.r][t.c] = 1;
					unRipeCnt--;
					if(unRipeCnt == 0) break outer;
					else ripe.offer(new Tomato(t.h + 1,t.r,t.c));
				}
				
			}
			days++;
		}
		
		if(unRipeCnt > 0) days = -1;
		
		ans = days;
	}
	
	//상 우 하 좌
	static int[] dr1 = {-1,0,1,0};
	static int[] dc1 = {0,1,0,-1};
	
	private static boolean possibleRipe() {
		
		while( !unRipe.isEmpty()) {
			Tomato t = unRipe.poll();
			int nr, nc;
			boolean result = false;
			for(int d=0; d < 4; d++) {
				nr = t.r + dr1[d];
				nc = t.c + dc1[d];
				if(outToMap(nr,nc)) continue;
				if(map[t.h][nr][nc] == -1) continue;
				result = true;
			}
			if( !result && t.h - 1 > -1 && map[t.h - 1][t.r][t.c] != -1 ) result = true;
			if( !result && t.h + 1 < H && map[t.h + 1][t.r][t.c] != -1 ) result = true;
			
			if(!result) return false;
		}
		
		return true;
	}
	
	private static boolean outToMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M);
	}
	
}
