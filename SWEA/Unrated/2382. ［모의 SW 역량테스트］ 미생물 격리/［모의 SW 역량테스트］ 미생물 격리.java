import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * SWEA 2382 - 미생물 격리
 * 주어지는 값
 * T : 테스트케이스 수
 * N : 맴의 크기
 * M : 타겟 시간
 * K : 초기 미생물 군집 수
 * 2번째 줄부터 K번째 줄 : 초기 미생물 군집의 정보들 
 *  
 * 정사각형 구역 안에 K개의 미생물 군집
 * N x N의 정사각형 크기
 * 가장 바깥쪽에는 약품이 칠해져 있어 미생물이 접근 불가
 * 최초 각 미생물 군집의 위치와 군집 내 미생물의 수, 이동 방향이 주어짐
 * 이동 방향은 상 하 좌 우
 * 각 군집들은 1시간마다 이동방향에 있는 다음 셀로 이동
 * 미생물 군집이 약품이 칠해진 곳으로 이동하면 미생물의 절반이 죽고, 이동방향이 반대로 바뀜
 * 미생물이 한마리일 경우 약품이 칠해진 곳으로 가면 사라짐
 * 두 개 이상의 군집이 한 곳에 모일 경우 군집들이 합쳐짐
 * 합쳐진 군집의 미생물 수는 군집들의 미생물 수 합이며, 이동 방향은 가장 많았던 군집의 이동방향으로 설정
 * M시간 후 남아있는 미생물 수의 총합을 구하여라
 * 시뮬레이션
 * 1. 미생물군집을 관리할 class 생성
 * 2. 해당 클래스로 Queue 구성해서 시뮬레이션 실행
 * 
*/
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static class Bug implements Comparable<Bug>{
		int r, c, amount, dir;

		public Bug(int r, int c, int amount, int dir) {
			this.r = r;
			this.c = c;
			this.amount = amount;
			this.dir = dir;
		}

		@Override
		public int compareTo(Bug o) {
			return this.amount - o.amount;
		}
		
	}
	
	static int N, M, K;
	static int[][][] map;
	static PriorityQueue<Bug> bugs;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			//초기화
			bugs = new PriorityQueue<>();
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N][2];
			for(int i=0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				bugs.offer(new Bug(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1));
			}
			
			simulate(0);
			
			int ans = 0;
			while(!bugs.isEmpty()) ans += bugs.poll().amount;
			
			//출력
			bw.write("#" + t + " " + ans + "\n");
		}
		bw.close();
	}
	
	//상 하 좌 우
	static int[] dr1 = {-1,1,0,0};
	static int[] dc1 = {0,0,-1,1};
	private static void simulate(int time) {
		if(time == M || bugs.size() == 0) return; //시간이 다되었거나 이미 미생물 군집을 다 처리한 경우
		
		int size = bugs.size();
		
		Bug b;
		int nr, nc, dir;
		for(int i=0; i < size; i++) {
			b = bugs.poll();
			dir = b.dir;
			nr = b.r + dr1[dir];
			nc = b.c + dc1[dir];

			//약품 위치에 들어온 경우
			if(medicationZone(nr,nc)) {
				map[nr][nc][0] += b.amount / 2;
				if(dir < 2) map[nr][nc][1] = 1 - dir;
				else map[nr][nc][1] = 5 - dir;				
			} else {
				map[nr][nc][0] += b.amount;
				map[nr][nc][1] = dir;
			}
		}
		
		for(int i=0; i < N; i++) {
			for(int j=0; j < N; j++) {
				if(map[i][j][0] > 0) {
					bugs.offer(new Bug(i, j, map[i][j][0], map[i][j][1]));
					map[i][j][0] = 0;
					map[i][j][1] = -1;
				}
			}
		}
		
		simulate(time + 1);
	}
	
	private static boolean medicationZone(int r, int c) {
		return (r < 1 || r >= N - 1 || c < 1 || c >= N - 1);
	}
}
