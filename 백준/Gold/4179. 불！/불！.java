import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 4179 - 불
 * 지훈이를 미로에서 탈출하도록 도와주자!
 * 지훈이와 불은 매 분마다 사방으로 이동
 * 불은 사방으로 확산
 * 지훈이는 미로의 가장자리에 도착하면 탈출 가능
 * 벽은 통과하지 못함
 * */
public class Main {
	static int R, C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		boolean[][] visited = new boolean[R][C];
		Queue<Point> survive = new LinkedList<>();
		Queue<Point> fire = new LinkedList<>();
		for(int i=0; i < R; i++) {
			int j=0;
			for(char c : br.readLine().toCharArray()) {
				map[i][j] = c;
				if(c == 'J') {
					survive.add(new Point(i, j));
					visited[i][j] = true;
				}
				else if(c == 'F') {
					fire.add(new Point(i, j));
					visited[i][j] = true;
				}
				j++;
			}
		}
		
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		
		boolean result = false;
		int cnt = 0, nr, nc;
		Point cur;
		while(!survive.isEmpty() && !result) {
			int fireSize = fire.size();
			for(int i=0; i < fireSize; i++) {
				cur = fire.poll();
				for(int d=0; d < 4; d++) {
					nr = cur.x + dr1[d];
					nc = cur.y + dc1[d];
					//맵에서 벗어나거나 이미 방문한 곳이거나 벽인 경우 패스
					if(outMap(nr, nc) || visited[nr][nc] || map[nr][nc] == '#') continue;
					
					visited[nr][nc] = true;
					fire.add(new Point(nr, nc));
				}
			}
			
			int surviveSize = survive.size();
			outer : for(int i=0; i < surviveSize; i++) {
				cur = survive.poll();
				for(int d=0; d < 4; d++) {
					nr = cur.x + dr1[d];
					nc = cur.y + dc1[d];
					//가장자리에 도착한 경우
					if(outMap(nr, nc)) {
						result = true;
						break outer;
					}
					//이미 방문한 곳이거나 벽이면 패스
					if(visited[nr][nc] || map[nr][nc] == '#') continue;
					visited[nr][nc] = true;
					survive.add(new Point(nr, nc));
				}
			}
			
			cnt++;
		}
		
		
		if(result)bw.write(String.valueOf(cnt));
		else bw.write("IMPOSSIBLE");
		
		bw.close();
		br.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
}