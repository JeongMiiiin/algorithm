import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 7562 - 나이트의 이동
 * 테스트 케이스 수 주어짐
 * 각 테스트 케이스는 3줄로 이루어짐
 * 첫째 줄에는 체스판 한 변의 길이가 주어짐
 * 두 번째 줄에는 나이트가 현재 있는 곳
 * 세 번째 줄에는 나이트가 가야할 곳
 * 최소로 얼마만큼 이동으로 갈 수 있는지 계산하라
 * 
 * 
 * */

public class Main {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		int[] dr1 = {-1, -2, -2, -1, 1, 2, 2, 1};
		int[] dc1 = {-2, -1, 1, 2, 2, 1, -1, -2};
		Queue<Point> q = new LinkedList<>();
		StringTokenizer st;
		int curR, curC, targetR, targetC, nr, nc;
		Point cur;
		for(int t=0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			curR = Integer.parseInt(st.nextToken());
			curC = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			targetR = Integer.parseInt(st.nextToken());
			targetC = Integer.parseInt(st.nextToken());
			
			q.clear();
			boolean[][] visited = new boolean[N][N];
			visited[curR][curC] = true;
			int cnt = 0;
			q.add(new Point(curR, curC));
			outer : while(!q.isEmpty()) {
				int size = q.size();
				for(int i=0; i < size; i++) {
					cur = q.poll();
					if(cur.x == targetR && cur.y == targetC) break outer;
					
					for(int d=0; d < 8; d++) {
						nr = cur.x + dr1[d];
						nc = cur.y + dc1[d];
						if(!outMap(nr, nc) && !visited[nr][nc]) {
							visited[nr][nc] = true;
							q.add(new Point(nr, nc));
						}
					}
				}
				
				
				cnt++;
			}
			System.out.println(cnt);
			
		}
		
		bw.close();
		br.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
}