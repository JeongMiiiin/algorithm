import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 16234 - 인구 이동
 *  
 * 주어지는 값
 * N : 지도의 가로 세로 크기
 * L : 인구 차이 최소값
 * R : 인구 차이 최대값
 *  
 * N x N 크기의 땅
 * 각각의 땅에는 나라가 하나씩 존재
 * r행 c열에 있는 나라에는 A[r][c]명이 살고 있음
 * 인접한 나라 사이에는 국경선이 존재
 * 인구이동의 규칙
 * 1. 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면 오늘 하루동안 국경선 오픈
 * 2. 위의 조건에 의해 열어야 하는 국경선이 모두 열렸다면, 인구 이동 시작
 * 3. 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루동안 연합이라고 함
 * 4. 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 됨.
 * 5. 연합을 해체하고, 모든 국경선을 닫음.
 * 
 * 각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램 작성
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static class Country {
		int r, c, people;

		public Country(int r, int c, int people) {
			this.r = r;
			this.c = c;
			this.people = people;
		}
	}
	
	static int N, L, R, ans = 0;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		//맵 구성
		map = new int[N][N];
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		dividePeople();
		
		System.out.println(ans);
	}
	
	private static void dividePeople() {
		
		int[][] newMap = new int[N][N];
				
		int idx = 0;
		for(int i=0; i < N; i++) for(int j=0; j < N; j++) if(newMap[i][j] == 0) bfs(newMap, i, j, ++idx);
		
		if(idx == N * N) return; //각각 나눠질 때 (모든 나라의 인구 수 차이가 조건에 부합하지 않을 때
		
		ans++; //인구 수 차이가 조건에 부합하는 게 있다는 뜻으로 일 수 증가
		dividePeople();
	}

	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	private static void bfs(int[][] newMap, int r, int c, int num) {
		Queue<Country> q = new ArrayDeque<>();
		newMap[r][c] = num;
		q.offer(new Country(r, c, map[r][c]));
		
		int cnt = 0, sum = 0;
		Country cur;
		while( !q.isEmpty() ) {
			cur = q.poll();
			cnt++;
			sum += cur.people;
			int nr, nc, nPeople;
			for(int d=0; d < 4; d++) {
				nr = cur.r + dr1[d];
				nc = cur.c + dc1[d];
				if(outMap(nr, nc) || newMap[nr][nc] != 0) continue;
				nPeople = map[nr][nc];
				
				//L명 이상 R명 이하면 같은 연합으로 간주
				if( L <= Math.abs(cur.people - nPeople) && Math.abs(cur.people - nPeople) <= R ) {
					newMap[nr][nc] = num;
					q.offer(new Country(nr, nc, nPeople));
				}
			}
		}
		
		int sumDivide = sum / cnt;
		for(int i=0; i < N; i++) for(int j=0; j < N; j++) if(newMap[i][j] == num) map[i][j] = sumDivide;
		
	}

	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	} 
	
}