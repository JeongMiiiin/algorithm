import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 2178번 - 미로찾기
*/
public class Main {
	static int N, M, ans;
	static int[][] map, visited;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new int[N][M];
		
		for(int i=0; i < N; i++) {
			char[] cList = br.readLine().toCharArray();
			for(int j=0; j < M; j++)
				if(cList[j] != '0') map[i][j] = 1;

		}
		
		bfs();
		
		System.out.println(ans);
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		//상,우,하,좌
		int[] dr1 = {-1,0,1,0};
		int[] dc1 = {0,1,0,-1};
		
		q.offer(new int[]{0,0}); //첫번째
		visited[0][0] = 1;
		
		int level = 1;
		while(!q.isEmpty()) {
			outer : for(int i=0, size = q.size(); i < size; i++) {
				int[] current = q.poll();
				for(int j=0; j < 4; j++) {
					int r = current[0] + dr1[j];
					int c = current[1] + dc1[j];
					
					//배열을 벗어날 경우
					if(r < 0 || r >= N || c < 0 || c >= M) continue;
					
					if(map[r][c] == 1 && visited[r][c] == 0) {
						visited[r][c] = 1;
						q.offer(new int[]{r,c});
						if(r == N - 1 && c == M - 1) {
							q.clear();
							break outer;
						}
					}
				}
			}
			level++;
		}
		
		ans = level;
	}
}
