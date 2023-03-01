import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 1018번 체스 칠하기
*/
public class Main {
	static int ans = 64;
	static boolean[][] board;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		board = new boolean[N][M];
		for(int i=0; i < N; i++) {
			String s = br.readLine();
			for(int j=0; j < M; j++) {
				if(s.charAt(j) == 'W') board[i][j] = true;
			}
				
		}
			
		
		for(int r=0; r < N - 7; r++) { //가로 케이스 계산
			for(int c=0; c < M -7; c++) { //세로 케이스 계산
				checkChess(r,c);
			}
		}
		
		System.out.println(ans);
	}

	private static void checkChess(int r, int c) {
		int endR = r + 8;
		int endC = c + 8;
		int cnt = 0;
		
		boolean TF = board[r][c];
		
		for(int i=r; i < endR; i++) {
			for(int j=c; j < endC; j++) {
				if(board[i][j] != TF) cnt++;
				
				TF = !TF;
			}
			
			TF = !TF;
		}
		
		cnt = Math.min(cnt, 64 - cnt);
		
		ans = Math.min(ans, cnt);
	}
}
