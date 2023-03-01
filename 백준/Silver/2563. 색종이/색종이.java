import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 2563번 색종이
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[101][101];
		
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int r=0; r < 10; r++) {
				for(int c=0; c < 10; c++) {
					map[x+r][y+c] = 1;
				}
			}
			
		}
		
		int cnt = 0;
		for(int i=1; i <= 100; i++)
			for(int j=1; j <= 100; j++)
				if(map[i][j] == 1) cnt++;
		System.out.println(cnt);
	}
}
