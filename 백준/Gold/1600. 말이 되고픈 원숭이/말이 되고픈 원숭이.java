import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 1600 - 말이 되고픈 원숭이
 * 
 * 주어지는 값
 * K : 말처럼 움직일 수 있는 횟수
 * W : 격자판의 가로길이
 * H : 격자판의 세로길이
 * 격자판의 상태
 * 0 : 평지, 1 : 장애물
 * 시작점에서 도착점까지 갈 수 없는 경우엔 -1 출력
 * 
 * 원숭이가 말처럼 움직이고 싶어한다.
 * 말은 체스판의 나이트와 같은 이동방식을 가짐.
 * 원숭이는 총 K번만 위와 같이 움직일 수 있고, 그 외에는 인접한 칸으로만 움직일 수 있음(상하좌우)
 * 원숭이는 격자판의 맨 왼쪽 위에서 맨 오른쪽 아래까지 이동해야 함.
 * 1. 인접한 4방향으로 움직이는 것.
 * 2. 말의 움직임으로 한 번 움직이는 것.
 * 최소한의 동작으로 시작지점에서 도착지점까지 갈 수 있는 방법을 알아내는 프로그램을 작성하시오. 
*/
public class Main {
	static class Monkey{
		int x, y, horseCnt, totalCnt;
	 
		public Monkey(int x, int y, int horseCnt, int totalCnt) {
		  this.x = x;
		  this.y = y;
		  this.horseCnt = horseCnt;
		  this.totalCnt = totalCnt;
		}
	} 
	
	static int K, W, H;
	static boolean[][][] visited;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//입력
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		visited = new boolean[H][W][K + 1];
		
		//격자판 세팅
		map = new int[H][W];
		for(int i=0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < W; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(bfs());
		
	}
	
	//상 우 하 좌 좌좌상 좌상상 우상상 우우상 우우하 우하하 좌하하 좌좌하
	static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[] hx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] hy = {-1, 1, -2, 2, -2, 2, -1, 1};
	private static int bfs() {
		Queue<Monkey> pq = new LinkedList<>();
		
		pq.offer(new Monkey(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		Monkey cur;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			//최종 목적지에 도달했을 때
			if(cur.x == H - 1 && cur.y == W - 1) return cur.totalCnt;
			
			int nx, ny;
			//말로 방문
			if(cur.horseCnt < K) {
				for(int d=0; d < 8; d++) {
					nx = cur.x + hx[d];
					ny = cur.y + hy[d];
					if( !outMap(nx, ny) && !visited[nx][ny][cur.horseCnt + 1] && map[nx][ny] != 1) {
						visited[nx][ny][cur.horseCnt + 1] = true;
						pq.offer(new Monkey(nx, ny, cur.horseCnt + 1, cur.totalCnt + 1));
					}
				}
			}
			
			for(int d=0; d < 4; d++) {
				nx = cur.x + dx[d];
				ny = cur.y + dy[d];
				if( !outMap(nx, ny) && !visited[nx][ny][cur.horseCnt] && map[nx][ny] != 1) {
					visited[nx][ny][cur.horseCnt] = true;
					pq.offer(new Monkey(nx, ny, cur.horseCnt, cur.totalCnt + 1));
				}
			}
		}
		
		return -1;
	}
	
	

	private static boolean outMap(int x, int y) {
		return (x < 0 || x >= H || y < 0 || y >= W);
	}
	
}