import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * SWEA 1861 정사각형 방
 * 주어지는 값
 * T : 테스트 케이스 수
 * N : 정사각형의 크기
 * N번째 줄의 N개의 수 : 정사각형을 차지하는 숫자들
 * 제약조건
 * 인접한 곳으로 움직이는데 그 숫자는 현재 숫자보다 정확히 1 더 커야 한다.
 * 
 * 출력값
 * 움직일 수 있는 방의 최대 개수와 최대 개수를 가질 수 있는 출발선의 방
 * 단, 최대 개수를 가질 수 있는 방이 2개 이상일 경우 가장 작은 값으로 도출
*/
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int ans; //출발해야하는 방의 숫자
	static int max; //방의 최대 개수
	static int N; //정사각형의 크기
	static int[][] map; //방들의 숫자를 담을 2차원 배열
	static int[][] visited; //방문을 담을 배열
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t <= T; t++) {
			//입력
			N = Integer.parseInt(br.readLine());
			
			//출력해야할 값 초기화
			ans = -1;
			max = -1;
			map = new int[N][N];
			
			for(int i=0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i < N; i++)
				for(int j=0; j < N; j++) {
					visited = new int[N][N];
					bfs(i,j);
				}
					
			
			//출력
			System.out.println("#" + t + " " + ans + " " + max);
		}
	}

	private static void bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		//상,우,하,좌
		int[] dr1 = {-1,0,1,0};
		int[] dc1 = {0,1,0,-1};
		
		q.offer(new int[] {i,j});
		if(visited[i][j] == 1) return;
		
		visited[i][j] = 1;
		
		int level = 0;
		while(!q.isEmpty()) {
			for(int z=0, size = q.size(); z < size; z++) {
				int[] current = q.poll();
				int currentNum = map[current[0]][current[1]];
				for(int v=0; v < 4; v++) {
					int r = current[0] + dr1[v];
					int c = current[1] + dc1[v];
					
					//배열을 벗어날 경우
					if(r < 0 || r >= N || c < 0 || c >= N) continue;
					
					if(map[r][c] == currentNum + 1 && visited[r][c] == 0) {
						visited[r][c] = 1;
						q.offer(new int[] {r,c});
					}
				}
			}
			level++;
			if(max < level) {
				max = level;
				ans = map[i][j];
			} 
			else if(max == level) ans = Math.min(ans, map[i][j]);
		}
		
	}
}
