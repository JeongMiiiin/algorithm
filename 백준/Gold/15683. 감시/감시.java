import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * 백준 15683 - 감시
 * N x M 크기의 사무실
 * 총 K개의 CCTV
 * CCTV 종류
 * 1. 우향
 * 2. 좌우향
 * 3. 상우향
 * 4. 좌상우향
 * 5. 좌상우하향
 * CCTV는 바라보고 있는 방향 전체 탐색 가능.
 * 단, 벽을 투과해서 볼 수는 없다.
 * 회전은 항상 90도로
 * 감시하려고 하는 방향이 가로 또는 세로
 * 맵에서 0은 빈 공간, 6은 벽, 1 - 5는 CCTV의 번호
 * 사각지대의 최소값을 출력하라
*/
public class Main {
	static class CCTV {
		int r;
		int c;
		int category;
		public CCTV(int r, int c, int category) {
			this.r = r;
			this.c = c;
			this.category = category;
		}
	}
	
	static int N, M, ans = Integer.MAX_VALUE, walls;
	static int[][] map;
	static List<CCTV> cctvList;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		cctvList = new ArrayList<>();
		map = new int[N][M];
		for(int i=0; i < N; i++) {
			for(int j=0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] > 0 && map[i][j] < 6) cctvList.add(new CCTV(i,j,map[i][j]));
				if(map[i][j] == 6) walls++;
			}
		}
		
		boolean[][] checkMap = new boolean[N][M];
		
		bfs(0, 0, checkMap);
		
		//출력
		System.out.println(ans);
		sc.close();
	}
	private static void bfs(int cnt, int sum, boolean[][] tempMap) {
		if(cnt == cctvList.size()) {
			int blind = (N * M) - sum - cctvList.size() - walls;
			if(blind < 0) blind = 0;
			ans = Math.min(ans, blind);
			return;
		}
		CCTV cctv = cctvList.get(cnt); //현재 선택된 cctv
		int tempSum = 0, r = cctv.r, c = cctv.c;
		switch(cctv.category) {
			case 1 :
				for(int d=0; d < 4; d++) {
					boolean[][] checkMap = new boolean[N][M];
					for(int i=0; i < N; i++) checkMap[i] = Arrays.copyOf(tempMap[i], M);
					tempSum = check(r,c,d,0,checkMap);
					bfs(cnt + 1, sum + tempSum, checkMap);
				}
				break;
			case 2 :
				for(int d=0; d < 2; d++) {
					tempSum = 0;
					boolean[][] checkMap = new boolean[N][M];
					for(int i=0; i < N; i++) checkMap[i] = Arrays.copyOf(tempMap[i], M);
					tempSum += check(r,c,d,0,checkMap);
					tempSum += check(r,c,d + 2,0,checkMap);
					bfs(cnt + 1, sum + tempSum, checkMap);
				}
				break;
			case 3 :
				for(int d=0; d < 4; d++) {
					tempSum = 0;
					boolean[][] checkMap = new boolean[N][M];
					for(int i=0; i < N; i++) checkMap[i] = Arrays.copyOf(tempMap[i], M);
					tempSum += check(r,c,d,0,checkMap);
					tempSum += check(r,c,d + 3,0,checkMap);
					bfs(cnt + 1, sum + tempSum, checkMap);
				}
				break;
			case 4 :
				for(int d=0; d < 4; d++) {
					tempSum = 0;
					boolean[][] checkMap = new boolean[N][M];
					for(int i=0; i < N; i++) checkMap[i] = Arrays.copyOf(tempMap[i], M);
					tempSum += check(r,c,d,0,checkMap);
					tempSum += check(r,c,d + 2,0,checkMap);
					tempSum += check(r,c,d + 3,0,checkMap);
					bfs(cnt + 1, sum + tempSum, checkMap);
				}
				break;
			case 5 :
				boolean[][] checkMap = new boolean[N][M];
				for(int i=0; i < N; i++) checkMap[i] = Arrays.copyOf(tempMap[i], M);
				for(int d=0; d < 4; d++) {
					tempSum += check(r,c,d,0,checkMap);
				}
				bfs(cnt + 1, sum + tempSum, checkMap);
				break;
		}
	}
	
	
	//해당방향으로 찾아보는 함수
	//우 하 좌 상
	static int[] dr1 = {0, 1, 0, -1};
	static int[] dc1 = {1, 0, -1, 0};
 	private static int check(int r, int c, int dir, int cnt, boolean[][] checkMap) {
		if(r < 0 || r >= N || c < 0 || c >= M) {
			return cnt;
		}
		if(map[r][c] == 6) return cnt;
		
		if(map[r][c] == 0 && !checkMap[r][c]) {
			checkMap[r][c] = true;
			cnt++;
		}
		
		int nr = r + dr1[dir % 4];
		int nc = c + dc1[dir % 4];
		
		return check(nr, nc, dir, cnt, checkMap);
	}
}
