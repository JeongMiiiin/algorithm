import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 2206 - 벽 부수고 이동하기
 * N x M 행렬로 표현되는 맵이 있음.
 * 맵에서 0은 이동 가능한 곳. 1은 이동할 수 없는 벽이 있는 곳.
 * 당신은 (1,1)에서 (N,M)의 위치까지 최단거리로 이동하려고 함. (시작하는 칸과 끝나는 칸도 포함)
 * 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 경로가 짧아진다면, 벽을 한개까지 부수고 이동하여도 된다.
 * 한 칸에서 이동할 수 있는 칸은 사방
 * 
 * */
public class Main {
	static class Pos {
		int r, c, total, zeroCnt;
		Pos(int r, int c, int total, int zeroCnt){
			this.r = r;
			this.c = c;
			this.total = total;
			this.zeroCnt = zeroCnt;
		}
	}
	static int N, M;
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 1][M + 1];
		for(int i=1; i <= N; i++) {
			char[] cList = br.readLine().toCharArray();
			for(int j=1; j <= M; j++) if(cList[j - 1] == '1') map[i][j] = 1;
		}
		int[][][] result = new int[N + 1][M + 1][2];
		for(int i=1; i <= N; i++) {
			for(int j=1; j <= M; j++) Arrays.fill(result[i][j], Integer.MAX_VALUE);
		}
		
		
		int r = 1, c = 1;
		result[1][1][0] = 1;
		result[1][1][1] = 1;
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(r, c, 1, 0));
		Pos cur;
		int nr, nc;
		while(!q.isEmpty()) {
			cur = q.poll();
			for(int d=0; d < 4; d++) {
				nr = cur.r + dr1[d];
				nc = cur.c + dc1[d];
				if(outMap(nr, nc)) continue; //맵에서 벗어났으면 패스
				if(cur.zeroCnt == 1 && map[nr][nc] == 1) continue; //이미 벽을 부순 상태인데 벽을 만나면 패스
				if(cur.zeroCnt == 1 && result[nr][nc][1] <= cur.total + 1) continue;
				if(cur.zeroCnt == 0 && result[nr][nc][0] <= cur.total + 1) continue;
				
				if(map[nr][nc] == 1) {
					result[nr][nc][1] = cur.total + 1;
					q.add(new Pos(nr, nc, cur.total + 1, cur.zeroCnt + 1));
				} else {
					if(cur.zeroCnt > 0) {
						result[nr][nc][1] = cur.total + 1;
						q.add(new Pos(nr, nc, cur.total + 1, cur.zeroCnt));
					} else {
						result[nr][nc][0] = cur.total + 1;
						q.add(new Pos(nr, nc, cur.total + 1, cur.zeroCnt));
					}
					
				}
			}
		}
		
		
		int ans = Math.min(result[N][M][0], result[N][M][1]);
		if(ans == Integer.MAX_VALUE) ans = -1;
		
		System.out.println(ans);
		br.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 1 || r > N || c < 1 || c > M);
	}
}