import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for(int i=0; i < N; i++) {
			String s = br.readLine();
			for(int j=0; j < M; j++) map[i][j] = s.charAt(j) - 48;
		}
		int max = N <= M ? N : M;
		int result = 0;
		outer : while(result == 0) {
			for(int i=0; i < N - max + 1; i++) {
				for(int j=0; j < M - max + 1; j++) {
					if(map[i][j] == map[i][j + max - 1] && map[i][j] == map[i + max - 1][j] && map[i][j] == map[i + max - 1][j + max - 1]) {
						result = max * max;
						break outer;
					}
				}
			}
			
			max--;
		}
		System.out.println(result);
	}
}