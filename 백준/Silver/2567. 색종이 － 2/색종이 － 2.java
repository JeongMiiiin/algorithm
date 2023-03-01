import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[101][101];
		
		for(int i=0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			for(int j= r; j < r + 10; j++) for(int z= c; z < c + 10; z++) map[j][z] = 1;
		}
		
		int cnt = 0;
		
		int dx[] = {-1,1,0,0};
		int dy[] = {0,0,1,-1};
		
		for(int i=1; i<=100; i++) {
			for(int j=1; j<=100; j++) {
				if(map[i][j] == 0) continue;
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if(nx >= 1&& nx <= 100 && ny >=1 && ny <= 100 && map[nx][ny] == 0)
						cnt++;
					else if(nx < 1 || nx > 100 || ny < 1 || ny > 100)
						cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
