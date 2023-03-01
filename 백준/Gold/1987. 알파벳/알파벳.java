import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 1987번 알파벳
*/
public class Main {
	static int R, C, ans = 1;
	static int[][] map;
	static boolean[] checkAlphabet = new boolean[26]; //알파벳 사용했는지 판단하는 boolean 배열
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for (int i=0; i < R; i++){
			char[] c = br.readLine().toCharArray();
			for(int j=0; j < C; j++) map[i][j] = c[j] - 'A';
		}
		
		dfs(0, 0, 0); 
		
		System.out.println(ans);
	}
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	private static void dfs(int r, int c, int sum) {
		if (checkAlphabet[map[r][c]]) { // 0,0에 저장된 알파벳이 이미 한번 방문한 알파벳이라면,
			ans = Math.max(ans, sum); // 정답을 갱신해준다.
			return; // 조건에 만족하므로 리턴.
		} else {
			checkAlphabet[map[r][c]] = true;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr1[i];
				int nc = c + dc1[i];

				if (nr >= 0 && nr < R && nc >= 0 && nc < C) { //벗어나는 경우에만
					dfs(nr, nc, sum + 1);
				}

			}

			checkAlphabet[map[r][c]] = false;

		}
	}
}
