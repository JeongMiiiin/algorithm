import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 9465 - 스티커
 * 
 * 스티커 2n개 구매.
 * 스티커는 2행 n열로 배치됨
 * 스티커를 떼면 4분면에 위치한 스티커들은 사용할 수 없음
 * 뗄 수 있는 스티커의 최대값 -> 점수의 합이 최대가 되면서 서로 변을 공유하지 않는 스티커 집합
 * 
 * 
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	
	static int N;
	static int[][] map, dp;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[2][N];
			dp = new int[2][N];
			visited = new boolean[2][N];
			for(int i=0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			find(0,0);
			find(1,0);
			
			int max = -1;
			for(int i=0; i < 2; i++) for(int j=0; j < N; j++) max = Math.max(dp[i][j], max);
				
			bw.write(max + "\n");
		}
		bw.close();
	}
	private static int find(int r, int c) {
		if(c >= N) return 0; //범위를 벗어났을 때
		
		if(visited[r][c]) return dp[r][c]; //이미 구해놨을 때
		
		visited[r][c] = true;
		
		return dp[r][c] = Math.max(Math.max(find(1 - r, c + 1), find(r, c + 2)), find(1 - r, c + 2)) + map[r][c];
	}
}