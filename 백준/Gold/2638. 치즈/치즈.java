import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 백준 2638 - 치즈
 * 
 * N x M 격자판
 * 격자판 위에 치즈가 존재
 * 각 칸의 치즈는 적어도 2변 이상이 실내 온도의 공기과 접촉하면 한시간 뒤에 없어짐
 * 치즈 내부의 공간은 외부 공기와 접촉하지 않음.
 * 
 * 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 구하라
 * 
*/
public class Main {
	static class Cheese{
		int r, c;

		public Cheese(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Hole {
		int r, c;

		public Hole(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;
	static int[][] map;
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	static Set<Hole> holeList = new HashSet<>();
	static boolean[][] findHoleVisited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		findHoleVisited = new boolean[N][M];
		
		Queue<Cheese> q = new LinkedList<>();
		
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) q.offer(new Cheese(i, j)); //치즈가 있는 곳이면 q에 담기
			}
		}
		
		//구멍 찾기
		for(int i=1; i < N - 1; i++) for(int j=1; j < M - 1; j++) if( !findHoleVisited[i][j] && map[i][j] == 0) findHole(i, j);
		
		//치즈 녹이기 시작
		int time = 0;
		
		int nr, nc;
		Cheese cur;
		List<Cheese> removeList = new ArrayList<>();
		while(!q.isEmpty()) {
			int size = q.size();
			removeList.clear();
			
			for(int i=0; i < size; i++) {
				cur = q.poll();
				int airCnt = 0;
				for(int d=0; d < 4; d++) {
					nr = cur.r + dr1[d];
					nc = cur.c + dc1[d];
					if(map[nr][nc] == 0) airCnt++;
				}
				
				//사라져야하는 치즈인 경우
				if(airCnt > 1) removeList.add(cur);
				else q.offer(cur);
			}
			
			//치즈 삭제
			for(int i=0; i < removeList.size(); i++) map[removeList.get(i).r][removeList.get(i).c] = 0;
			
			checkHole();
			
			time++;
			
		}
		
		//출력
		System.out.println(time);
		
		br.close();
	}
	
	private static void checkHole() {
		Queue<Hole> q = new LinkedList<>();
		Hole h;
		int nr, nc;
		Iterator<Hole> iter = holeList.iterator();
		while(iter.hasNext()) {
			h = iter.next();
			for(int d=0; d < 4; d++) {
				nr = h.r + dr1[d];
				nc = h.c + dc1[d];
				if(map[nr][nc] == 0) {
					q.offer(h);
					break;
				}
			}
		}
		
		while(!q.isEmpty()) {
			h = q.poll();
			map[h.r][h.c] = 0;
			for(int d=0; d < 4; d++) {
				nr = h.r + dr1[d];
				nc = h.c + dc1[d];
				if(map[nr][nc] == 2) q.offer(new Hole(nr, nc));
			}
			holeList.remove(h);
		}
		
	}
	
	private static void findHole(int r, int c) {
		
		Hole h = new Hole(r, c);
		
		List<Hole> tempList =  new ArrayList<>();
		tempList.add(h);
		
		int nr, nc;
		Queue<Hole> q = new LinkedList<>();
		q.offer(h);
		findHoleVisited[h.r][h.c] = true;
		
		boolean flag = true;
		Hole cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			for(int d=0; d < 4; d++) {
				nr = cur.r + dr1[d];
				nc = cur.c + dc1[d];
				if(outMap(nr, nc)) continue;
				if(edgeMap(nr, nc)) {
					flag = false;
				}
				if(!findHoleVisited[nr][nc] && map[nr][nc] == 0) {
					findHoleVisited[nr][nc] = true;
					h = new Hole(nr, nc);
					q.offer(h);
					tempList.add(h);
				}
			}
		}
		if(flag) {
			for(int i=0; i< tempList.size(); i++) {
				holeList.add(tempList.get(i));
				map[tempList.get(i).r][tempList.get(i).c] = 2;
			}
		}
		
	}
	
	private static boolean edgeMap(int r, int c) {
		return (r < 1 || r >= N - 1 || c < 1 || c >= M - 1);
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M);
	}
}