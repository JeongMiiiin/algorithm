import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 20058 - 마법사 상어와 파이어스톰
 * 
 * 주어지는 값
 * N : 격자 크기
 * Q : 시전 횟수
 * L : 나누는 격자 크기
 * 
 * 파이어스톰 시전 시 격자를 2l 크기로 부분격자를 나눈 후,
 * 1. 각 격자마다 90도 회전
 * 2. 회전 후 상 우 하 좌에 얼음이 있는 칸이 3개 미만이면
 * 얼음의 양 1 줄임.
 * 
 * 시전을 모두 하고, 다음 2가지 구하기
 * 1. 남아있는 얼음의 합
 * 2. 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
*/

public class Main {
	static int N;
	static int[][] map;
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int total = 0;
		int bigCnt = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
		int Q = Integer.parseInt(st.nextToken());
		
		//맵 세팅
		map = new int[N][N];
		
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i < Q; i++) fireStorm(Integer.parseInt(st.nextToken()));
		
		//계산
		boolean[][] visited = new boolean[N][N];
		
		for(int i=0; i < N; i++) {
			for(int j=0; j < N; j++) {
				if(visited[i][j] || map[i][j] == 0) continue;
				
				int compareCnt = 1;
				total += map[i][j];
				Queue<Point> q = new LinkedList<>();
				q.add(new Point(i, j));
				visited[i][j] = true;
				Point cur;
				while(!q.isEmpty()) {
					cur = q.poll();
					for(int d=0; d < 4; d++) {
						int nr = cur.x + dr1[d];
						int nc = cur.y + dc1[d];
						
						if(outMap(nr, nc) || visited[nr][nc] || map[nr][nc] == 0) continue;
						
						visited[nr][nc] = true;
						compareCnt++;
						total += map[nr][nc];
						q.add(new Point(nr, nc));
					}
				}
				
				if(compareCnt == 1) continue;
				
				bigCnt = Math.max(compareCnt, bigCnt);
			}
		}
		
		//출력
		System.out.println(total);
		System.out.println(bigCnt);
	}

	private static void fireStorm(int target) {
		int n = (int) Math.pow(2, target);
		
		//부분 격자 개수
		int cnt = (N / n) * (N / n);
		
		int init = 0, nr = 0, nc = 0;
		
		//격자 나누기
		while(init++ < cnt) {
			
			//부분격자 세팅
			int[][] select = new int[n][n];
			
			int selectR = 0, selectC = 0;
			int mapR = nr, mapC = nc;
			for(int i=0; i < (n * n); i++) {
				select[selectR][selectC] = map[mapR][mapC];
				
				if(++selectC >= n) {
					selectR++;
					selectC = 0;
					mapR++;
					mapC = nc;
				} else mapC++;
			}
			
			//90도 회전
			turn90(select);
			
			//회전한 값 돌려놓기
			selectR = 0;
			selectC = 0;
			mapR = nr;
			mapC = nc;
			for(int i=0; i < (n * n); i++) {
				map[mapR][mapC] = select[selectR][selectC];
				
				if(++selectC >= n) {
					selectR++;
					selectC = 0;
					mapR++;
					mapC = nc;
				} else mapC++;
			}
			
			//다음번 격자 시작 위치 지정
			nc += n;
			if(nc >= N) {
				nr += n;
				nc = 0;
			}
			
		}
		
		//주변 얼음 체크
		boolean[][] iceVisited = new boolean[N][N];
		for(int i=0; i < N; i++) {
			for(int j=0; j < N; j++) {
				int iceCnt = 0;
				for(int d=0; d < 4; d++) {
					int iceR = i + dr1[d];
					int iceC = j + dc1[d];
					if(outMap(iceR, iceC) || map[iceR][iceC] == 0) continue;
					
					iceCnt++;
				}
				
				if(iceCnt < 3) iceVisited[i][j] = true;
			}
		}
		
		for(int i=0; i < N; i++) for(int j=0; j < N; j++) if(iceVisited[i][j]) if(--map[i][j] < 0) map[i][j] = 0;
		
	}
	
	private static void turn90(int[][] select) {
		int len = select.length;
		int[][] copy = new int[len][len];
		
		for(int r=0; r < len; r++) for(int c=0; c < len; c++) copy[c][len - r - 1] = select[r][c];
		
		for(int r=0; r < len; r++) for(int c=0; c < len; c++) select[r][c] = copy[r][c];
	}

	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
}