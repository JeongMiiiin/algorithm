import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 5427 - 불
 * 빈 공간과 벽으로 이루어진 건물에 갇혀 있음.
 * 건물의 일부에는 불이 났고, 상근이는 출구를 향해 뛰고 있음.
 * 매 초마다 불은 사방의 빈 공간으로 퍼져나감.
 * 상근이도 사방으로 움직일 수 있음.
 * 불이 있어나 이제 불이 붙으려는 칸으로 이동 불가능.
 * 상근이가 있던 곳에 불이 옮겨옴가 동시에 다른 칸으로 이동 가능.
 * 얼마나 빨리 탈출할 수 있을지 구하라.
 * 빌딩을 탈출할 수 없다면 IMPOSSIBLE 출력
 * 맵 바깥으로 나가면 끝나는 형태
 * 
 * 1. 멥 세팅
 * 2. BFS로 진행
 *  
 * */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		
		//테스트 케이스
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			Point init = new Point(0, 0);
			char[][] building = new char[w][h];
			Queue<Point> curFireq = new LinkedList<>();
			for(int i=0; i < w; i++) {
				int j=0;
				for(char c : br.readLine().toCharArray()) {
					if(c == '@') {
						init.x = i;
						init.y = j;
					} else if(c == '*') curFireq.add(new Point(i, j));
					building[i][j++] = c;
				}
			}
			
			boolean flag = false;
			int seconds = 0;
			boolean[][] visited = new boolean[w][h]; //상근이가 지나온 곳 표시
			visited[init.x][init.y] = true;
			Queue<Point> q = new LinkedList<>();
			q.add(init);
			Point cur, curFire;
			int nr, nc;
			outer : while(!q.isEmpty()) {
				seconds++;
				//불 퍼지기
				int curFireSize = curFireq.size();
				for(int i=0; i < curFireSize; i++) {
					curFire = curFireq.poll();
					for(int d = 0; d < 4; d++) {
						nr = curFire.x + dr1[d];
						nc = curFire.y + dc1[d];
						if(nr < 0 || nr >= w || nc < 0 || nc >= h || building[nr][nc] == '*' || building[nr][nc] == '#') continue;
						building[nr][nc] = '*';
						curFireq.add(new Point(nr, nc));
					}
				}
				
				//상근이 방문 시작
				int size = q.size();
				for(int i=0; i < size; i++) {
					cur = q.poll();
					for(int d=0; d < 4; d++) {
						nr = cur.x + dr1[d];
						nc = cur.y + dc1[d];
						//맵에서 벗어나면 종료
						if(nr < 0 || nr >= w || nc < 0 || nc >= h) {
							flag = true;
							break outer;
						}
						//벽이거나 불이 있거나 방문했던 곳이거나 불이 퍼질 곳이면 패스
						if(building[nr][nc] == '#' || building[nr][nc] == '*' || visited[nr][nc]) continue;
						visited[nr][nc] = true;
						q.add(new Point(nr, nc));
					}
				}
			}
			
			
			//결과 출력
			if(!flag) bw.write("IMPOSSIBLE\n");
			else bw.write(String.valueOf(seconds) + "\n"); 
		}
		
		
		bw.close();
		br.close();
	}
}