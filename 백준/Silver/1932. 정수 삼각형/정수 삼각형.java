import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 1932 - 정수 삼각형
 * 삼각형의 크기는 1 이상 500 이하
 * 
 * 정수삼각형이 주어짐.
 * 맨 위층에서 아래층까지 내려오면서 이제까지 선택된 수의 합이 최대가 되는 경로를 구하라
 * 아래층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.
 * 그 전값을 가장 최대의 값으로 저장
*/
public class Main {
	static int[][] NList;
	static boolean[][] dp;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		NList = new int[N + 1][(N * 2) + 1];
		dp = new boolean[N + 1][(N * 2) + 1]; 
		StringTokenizer st;
		for(int i=1, startIdx = 0, endIdx = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=startIdx; j < endIdx; j+=2) NList[i][N + j] = Integer.parseInt(st.nextToken());
			startIdx--;
			endIdx++;
		}
		
		for(int i=1; i <= N; i++) {
			//row 값과 col값을 줘야 함
			find(N, (i * 2) - 1);
		}
		
		int max = -1;
		for(int i=1; i <= N * 2; i++) max = Math.max(max, NList[N][i]);
		
		System.out.println(max);
	}
	private static int find(int row, int col) {
		if(row == 0) return NList[1][N + 1];
		
		if(dp[row][col]) return NList[row][col];
		dp[row][col] = true;
		
		if(col < (N - row) + 1 || col > (N * 2) - (N - row + 1)) return 0;
		
		return NList[row][col] += Math.max(find(row - 1, col - 1), find(row - 1, col + 1));
	}
}
