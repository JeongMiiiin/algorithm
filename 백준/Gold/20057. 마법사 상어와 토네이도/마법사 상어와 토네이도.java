import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 20057 - 마법사 상어와 토네이도
 * 
 * 마법사 상어가 토네이도를 배웠고
 * 오늘은 토네이도를 크기가 N x N인 격자로 나누어진 모래밭에서
 * 연습하려고 한다.
 * 위치 (r, c)는 격자의 r행 c열을 의미하고,
 * A[r][c]는 (r,c)에 있는 모래의 양을 의미
 * 
 * 토네이도를 시전하면 격자의 가운데 칸부터 토네이도의 이동이 시작
 * 토네이도는 한 번에 한 칸 이동
 * 
 * 토네이도가 한 칸씩 이동할 때마다
 * 모래는 일정한 비율로 흩날리게 됨.
 * 계산시에 소수점 아래는 버린다.
 * 
 */


public class Main {
	static int N, ans;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		ans = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		//맵 세팅
		map = new int[N][N];
		StringTokenizer st;
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//토네이도 시작점
		int sr = N / 2, sc = N / 2;
		int lineMax = 1;
		
		//토네이도 시작
		while(lineMax <= N) {
			int curD = 0;
			
			//짝수번째인 경우 우 상로 이동
			if(lineMax % 2 == 0) curD = 2;
			
			//좌우로 이동
			tonaido(sr, sc, curD, lineMax);
			
			sr += dr1[curD] * lineMax;
			sc += dc1[curD] * lineMax;
			
			curD++;
			//상하로 이동
			tonaido(sr, sc, curD, lineMax);
			
			sr += dr1[curD] * lineMax;
			sc += dc1[curD] * lineMax;
			
			if(++lineMax == N) {
				curD = 0;
				//좌로 이동
				tonaido(sr, sc, curD, lineMax - 1);
				
				break;
			}
		}
		
		System.out.println(ans);
		
	}
	
	//방향 좌 하 우 상
	static int[] dr1 = {0, 1, 0, -1};
	static int[] dc1 = {-1, 0, 1, 0};
	
	private static void tonaido(int sr, int sc, int dir, int lineMax) {
		int lineCnt = 0;
		
		int[] dr = {1, 3};
		if(dir % 2 == 1) {
			dr[0] = 0;
			dr[1] = 2;
		}
		
		int nr = sr + dr1[dir], nc = sc + dc1[dir];
		
		while(lineCnt++ < lineMax) {
			int sand = map[nr][nc];
			map[nr][nc] = 0;
			
			int[] ratio = {1, 7, 10};
			
			int ratioSand, ratioUpR, ratioUpC, ratioDownR, ratioDownC, moveSand = 0;
			
			nr -= dr1[dir] * 2;
			nc -= dc1[dir] * 2;
			
			//1%, 7%, 10% 분배
			for(int i=0; i < 3; i++) {
				ratioSand = (sand * ratio[i]) / 100;
				
				nr += dr1[dir];
				nc += dc1[dir];
				
				ratioUpR = nr + dr1[dr[1]];
				ratioUpC = nc + dc1[dr[1]];
				
				//맵에서 벗어나는 경우 ans에 더해주기
				if(outMap(ratioUpR, ratioUpC)) ans += ratioSand;
				else map[ratioUpR][ratioUpC] += ratioSand;
				
				moveSand += ratioSand;
				
				ratioDownR = nr + dr1[dr[0]];
				ratioDownC = nc + dc1[dr[0]];
				
				//맵에서 벗어나는 경우 ans에 더해주기
				if(outMap(ratioDownR, ratioDownC)) ans += ratioSand;
				else map[ratioDownR][ratioDownC] += ratioSand;
				
				moveSand += ratioSand;
			}
			
			//2% 분배
			nr -= dr1[dir];
			nc -= dc1[dir];
			
			ratioSand = (sand * 2) / 100;
			
			ratioUpR = nr + (dr1[dr[1]] * 2);
			ratioUpC = nc + (dc1[dr[1]] * 2);
			
			//맵에서 벗어나는 경우 ans에 더해주기
			if(outMap(ratioUpR, ratioUpC)) ans += ratioSand;
			else map[ratioUpR][ratioUpC] += ratioSand;
			
			moveSand += ratioSand;
			
			ratioDownR = nr + (dr1[dr[0]] * 2);
			ratioDownC = nc + (dc1[dr[0]] * 2);
			
			//맵에서 벗어나는 경우 ans에 더해주기
			if(outMap(ratioDownR, ratioDownC)) ans += ratioSand;
			else map[ratioDownR][ratioDownC] += ratioSand;
			
			moveSand += ratioSand;
			
			//5% 분배
			nr += dr1[dir] * 2;
			nc += dc1[dir] * 2;
			
			ratioSand = (sand * 5) / 100;
			
			if(outMap(nr, nc)) ans += ratioSand;
			else map[nr][nc] += ratioSand;
			
			moveSand += ratioSand;
			
			nr -= dr1[dir];
			nc -= dc1[dir];
			
			ratioSand = sand - moveSand;
			
			if(outMap(nr, nc)) ans += ratioSand;
			else map[nr][nc] += ratioSand;
			
			
		}
	}
	
	
	//맵에서 벗어나는지 확인하는 함수
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
}