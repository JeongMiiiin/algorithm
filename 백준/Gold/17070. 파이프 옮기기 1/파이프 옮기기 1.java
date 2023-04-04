import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 17070 - 파이프 옮기기 1
 * 
 * N x N의 격자판
 * 각각의 칸은 (r,c)로 표현됨
 * 행과 열의 번호는 1부터 시작
 * 각각의 칸은 빈 칸이거나 벽이다.
 * 
 * 집 수리를 위해서 파이프 하나를 옮기려고 한다.
 * 2개의 연속된 칸을 차지하는 크기
 * 파이프는 회전시킬 수 있음
 * 1. 가로 2. 세로 3. 우하대각선
 * 파이프는 항상 빈 칸만 차지해야 한다.
 * 파이프가 가로로 있을 때 움직일 수 있는 방향 -> 1. 우  2. 우하 대각선으로 바꾸면서 우하
 * 파이프가 세로로 있을 때 움직일 수 있는 방향 -> 1. 하  2. 우하 대각선으로 바꾸면서 우하
 * 파이프가 우하대각선으로 있을 때 움직일 수 있는 방향 -> 1. 우하  2. 가로 대각선으로 바꾸면서 우  3. 세로 대각선으로 바꾸면서 하
 * 
 * 꺾어서 내려가도 도달을 못할 경우를 백트래킹 하자
*/
public class Main {
	/*
	 * 끝의 가로점과 세로점 현재 dir의 상태를 담는 Pipe 클래스
	 * dir 상태
	 * 0 : 가로
	 * 1 : 세로
	 * 2 : 우하
	*/
	static class Pipe {
		int tailR, tailC, dir;

		public Pipe(int tailR, int tailC, int dir) {
			this.tailR = tailR;
			this.tailC = tailC;
			this.dir = dir;
		}
	}
	
	static int N;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		StringTokenizer st;
		for(int i=1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j <= N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		//우 하 우하
		int[] dr1 = {0, 1, 1};
		int[] dc1 = {1, 0, 1};
		
		Queue<Pipe> q = new LinkedList<>();
		q.offer(new Pipe(1,2,0));

		int ans = 0;
		
		Pipe cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			int r = cur.tailR;
			int c = cur.tailC;
			int dir = cur.dir;
			if(r == N && c == N) { //도착점에 도착했을 때 ans를 올리고 패스
				ans++;
				continue;
			}
			/*
			 * 방향에 따른 dir 선택
			 * 가로 -> 우, 우하 (상태변경) 0 2
			 * 세로 -> 하, 우하 (상태변경) 1 2
			 * 우하 -> 우하, 우 (상태변경), 하 (상태변경) 2 0 1
			*/
			
			int nr = r + dr1[2], nc = c + dc1[2];
			if(!outMap(nr, nc) && possibleDiagon(nr, nc)) q.offer(new Pipe(nr, nc, 2));
			
			//가로 진행
			if(dir != 1) {
				nr = r + dr1[0];
				nc = c + dc1[0];
				if(!outMap(nr,nc) && map[nr][nc] == 0) q.offer(new Pipe(nr, nc, 0));
			}
			
			if(dir != 0) {
				//세로 진행
				nr = r + dr1[1];
				nc = c + dc1[1];
				if(!outMap(nr,nc) && map[nr][nc] == 0) q.offer(new Pipe(nr, nc, 1));
			}
		}
		System.out.println(ans);	
	}
	
	//맵을 벗어나는지 판단해주는 함수
	private static boolean outMap(int r, int c) {
		return (r < 1 || r > N || c < 1 || c > N);
	}
	
	//대각선으로 진행할 수 있는지 판단해주는 함수
	private static boolean possibleDiagon(int r, int c) {
		return (map[r - 1][c] == 0 && map[r][c - 1] == 0 && map[r][c] == 0);
	}

	//꺾어서 내려갈 수 있는지 ㅎ
	/*private static boolean possibleFinal(int r, int c) {
		return (r >= N - c);
	}*/
	
}