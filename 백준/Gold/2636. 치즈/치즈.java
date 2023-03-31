import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 백준 - 치즈
 * 사각형 모양의 판이 있고, 그 위에 얋은 치즈가 있다.
 * 판의 가장자리에는 치즈가 놓여있지 않으며 치즈에는 하나 이상의 구멍이 있을 수 있다.
 * 공기와 접촉된 칸은 한 시간이 지나면 녹아 없어진다.
 * 치즈의 구멍 속에는 공기가 없지만 구멍을 둘러싼 치즈가 녹아서 구멍이 열리면
 * 구멍속으로 공기가 들어가게 된다.
 * 
 * 치즈가 모두 녹아 없어지는 데 걸리는 시간과
 * 모두 녹기 한 시간 전에 남아있는 치즈조각의 개수를 출력
 * 
*/
public class Main {
	static class Cheese {
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
	static int[][] map;
	static boolean[][] visited;
	static int R, C;
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	static Set<Hole> holeList = new HashSet<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Queue<Cheese> q = new ArrayDeque<>();
		
		map = new int[R][C];
		visited = new boolean[R][C];
		for(int i=0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) q.offer(new Cheese(i, j));
			}
		}
		
		//치즈로 둘러싸인 구멍 찾아서 표시해주는 함수
		for(int i=1; i < R - 1; i++) for(int j=1; j < C - 1; j++) if(map[i][j] == 0 && !visited[i][j]) findHole(i, j);
		
		int time = 0, resultCnt = 0, nr, nc;
		Cheese cur;
		List<Cheese> removeList = new ArrayList<>();
		while(!q.isEmpty()) {
			removeList.clear();
			resultCnt = q.size();
			for(int i=0; i < resultCnt; i++) {
				cur = q.poll();
				boolean flag = false; //주변에 공기가 있는지 확인 (있으면 true)
				for(int d=0; d < 4; d++) {
					nr = cur.r + dr1[d];
					nc = cur.c + dc1[d];
					if(map[nr][nc] == 0) {
						 flag = true;
						 break;
					}
				}
				if(flag) removeList.add(cur);
				else q.offer(cur);
			}
			for(int i=0; i < removeList.size(); i++) map[removeList.get(i).r][removeList.get(i).c] = 0;
			
			checkHole();
			
			time++;
		}
		System.out.println(time);
		System.out.println(resultCnt);
		
	}

	private static void checkHole() {
		Queue<Hole> q = new ArrayDeque<>();
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
		Queue<Hole> q = new ArrayDeque<>();
		q.offer(h);
		visited[h.r][h.c] = true;
		
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
				if(!visited[nr][nc] && map[nr][nc] == 0) {
					visited[nr][nc] = true;
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
		return (r < 1 || r >= R - 1 || c < 1 || c >= C - 1);
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
}