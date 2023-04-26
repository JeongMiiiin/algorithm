import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 21610 - 마법사 상어와 비바라기
 * 
 * 주어지는 값
 * N : 지도 크기
 * M : 명령 개수
 * 2 ~ N번째 줄 : 배열 정보
 * N + 2 ~ M번째 줄 : 명령 정보 
 *  
 * 마법사 상어는 파이어볼, 토네이도, 파이어스톰, 물복사버그 가능
 * 오늘 새로 배운 마법은 비바라기
 * 비바라기를 시전하면 하늘에 비구름을 만들 수 있다.
 * 오늘은 비바라기를 크기가 N X N인 격자에서 연습하려 함.
 * 격자의 각 칸에는 바구니가 하나 있고, 바구니는 칸 전체를 차지.
 * 바구니에 저장할 수 있는 물의 양에는 제한이 없음.
 * 격자의 가장 왼쪽 윗 칸은 1,1. 가장 오른쪽 아랫 칸은 N,N
 * 마법사 상어는 행과 열을 무한으로 연결함
 * 비바라기 시전 시 (N,1), (N,2), (N - 1,1), (N - 1, 2)에 비구름 생성
 * 이제 구름에 이동을 M번 명령 (방향과 거리)
 * 방향은 8방향 (좌 좌상 상 우상 우 우하 하 좌하)
 * 이동 명령시 다음 순서로 진행됨
 * 1. 모든 구름이 d방향으로 s칸 이동
 * 2. 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가
 * 3. 구름이 사라짐
 * 4. 2에서 물이 증가한 칸에 물복사버그 마법 시전.
 * 4-1. 물복사버그는 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수 만큼 (r,c) 물의 양이 증가
 * 5.바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어듬.
 * 5-1. 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 함.
 * 
 * M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합 구하기
 * 
*/
public class Main {
	
	static class Cloud {
		int r, c, cnt;

		public Cloud(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static int N, M;
	static int[][] map;
	static Queue<Cloud> clouds;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];
		clouds = new LinkedList<>();
		for(int i=1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j <= N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		//처음 구름 상태 만들기
		for(int i= N - 1; i <= N; i++) for(int j=1; j <= 2; j++) clouds.add(new Cloud(i, j, 1));
		
		//이동명령 수행
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken()) - 1;
			int dis = Integer.parseInt(st.nextToken());
			move(dir, dis);
		}
		
		
		//최종 물의 합 구하기
		int ans = 0;
		for(int i=1; i <= N; i++) for(int j=1; j <= N; j++) ans += map[i][j];
		
		//출력
		System.out.println(ans);
		br.close();
	}
	
	//좌 좌상 상 우상 우 우하 하 좌하
	static int[] dr1 = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc1 = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	//구름 이동 함수	
	private static void move(int dir, int dis) {
		
		Queue<Cloud> arrive = new LinkedList<>();
		
		//구름 전부 이동 후 해당 위치 1 증가
		Cloud cur;
		while(!clouds.isEmpty()) {
			cur = clouds.poll();
			int nr = cur.r + (dr1[dir] * dis);
			int nc = cur.c + (dc1[dir] * dis);
			//더 크게 나온 경우
			if(nr > N) {
				int share = nr / N;
				nr -= (N * share);
			}
			if(nc > N) {
				int share = nc / N;
				nc -= (N * share);
			}
			//더 작게 나온 경우
			if(nr < 1) {
				int share = (-nr) / N != 0 ? ((-nr) / N) : 0;
				share++;
				nr += (N * share);
			}
			if(nc < 1) {
				int share = (-nc) / N != 0 ? (-nc) / N : 0;
				share++;
				nc += (N * share);
			}
			map[nr][nc]++; //해당 위치 값 올리기
			visited[nr][nc] = true; //방문 처리
			arrive.add(new Cloud(nr, nc, 0));
		}
		
		int arriveSize = arrive.size();
		
		for(int i=0; i < arriveSize; i++) {
			cur = arrive.poll();
			int cnt = 0;
			for(int d=1; d <= 7; d += 2) {
				int nr = cur.r + dr1[d];
				int nc = cur.c + dc1[d];
				if(!outMap(nr, nc) && map[nr][nc] > 0) cnt++;
			}
			if(cnt > 0) arrive.add(new Cloud(cur.r, cur.c, cnt));
		}
		
		while( !arrive.isEmpty() ) {
			cur = arrive.poll();
			map[cur.r][cur.c] += cur.cnt;
		}
		
		makeCloud();
	}

	//구름 만들기 함수
	private static void makeCloud() {
		for(int i=1; i <= N; i++) {
			for(int j=1; j <= N; j++) {
				if(map[i][j] >= 2 && !visited[i][j]) {
					map[i][j] -= 2;
					clouds.add(new Cloud(i, j, 1));
				}
				//구름이 사라진 곳이면 방문 되돌리기
				if(visited[i][j]) visited[i][j] = false;
				
			}
		}
	}
	
	private static boolean outMap(int r, int c) {
		return ( r < 1 || r > N || c < 1 || c > N);
	}
	
}