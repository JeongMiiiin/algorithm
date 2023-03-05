import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * SWEA 5653 - 줄기세포 배양 프로그램
 * 주어지는 값
 * N : 초기 배양세포 영역 세로 크기
 * M : 초기 배양세포 영역 가로 크기
 * K : 배양시간
 * 
 * 하나의 줄기 세포는 가로, 세로 크기가 1인 정사각형 형태로 존재
 * 각 줄기 세포는 생명력이라는 수치를 가지고 있음
 * 초기 상태에서 줄기 세포들은 비활성 상태
 * 생명력 수치가 X인 줄기 세포의 경우 X시간동안 비활성상태, X시간이 지나는 순간 활성상태
 * 활성상태일 때는 X시간동안 살아있을 수 있으며 X시간이 지나면 세포는 죽게 됨
 * 세포가 죽더라도 소멸되지 않고, 그 자리에 죽은 상태로 존재
 * 활성화된 줄기 세포는 첫 1시간 동안 상, 하, 좌, 우 네 방향으로 동시에 번식
 * 번식된 줄기세포는 비활성 상태
 * 두 개 이상의 줄기 세포가 하나의 그리드 셀에 동시 번식하려고 하는 경우 생명력 수치가 높은 줄기 세포가 해당 그리드 셀을 차지
 * 
 * 
*/
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static class Cell implements Comparable<Cell>{
		int r, c, vitality, waitingTime = 0, activeTime = 0;
		boolean active = false, dead = false;
		
		public Cell(int r, int c, int vitality) {
			this.r = r;
			this.c = c;
			this.vitality = vitality;
		}


		@Override
		public int compareTo(Cell o) {
			return o.vitality - this.vitality;
		}
		
	}
	
	static int N, M, K;
	static int[][] map;
	static boolean[][] visited;
	static List<Cell> cells;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t <= T; t++) {
			map = new int[1001][1001];
			visited = new boolean[1001][1001];
			cells = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			for(int i=0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j < M; j++) {
					int n = Integer.parseInt(st.nextToken());
					if(n > 0) {
						map[500 - N / 2 + i][500 - M / 2 + j] = n;
						Cell c = new Cell(500 - N / 2 + i, 500 - M / 2 + j, n);
						visited[500 - N / 2 + i][500 - M / 2 + j] = true;
						cells.add(c);
					}
				}
			}
			
			simulate(0);
			
			int ans = 0;
			for(int i=0; i < cells.size(); i++) if( !cells.get(i).dead ) ans++;
			
			//출력
			bw.write("#" + t + " " + ans + "\n");
		}
		bw.close();
	}
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	private static void simulate(int time) {
		if(time == K) return; //시간이 되면 종료
		
		PriorityQueue<Cell> activeCells = new PriorityQueue<>();
		Cell c;
		for(int i=0; i < cells.size(); i++) {
			c = cells.get(i);
			if(!c.dead && c.active) { //활성 상태이지만 죽지 않았을 때
				c.activeTime++;
				if(c.activeTime == c.vitality) { //생명력이 다했을 때
					c.dead = true;
				}
			} else if(!c.active) { //아직 활성상태가 아닐 때
				if(c.waitingTime >= c.vitality) { //활성상태가 되어야할 때
					c.active = true;
					c.activeTime++;
					activeCells.offer(c);
					if(c.activeTime == c.vitality) c.dead = true;
				} else c.waitingTime++;
			}
		}
		
		while(!activeCells.isEmpty()) {
			c = activeCells.poll();
			int nr, nc;
			for(int d=0; d < 4; d++) {
				nr = c.r + dr1[d];
				nc = c.c + dc1[d];
				if(visited[nr][nc]) continue; //이미 누가 선점했을 때
				visited[nr][nc] = true;
				cells.add(new Cell(nr, nc, c.vitality));
			}
		}
		
		
		simulate(time + 1);
	}
	
}
