import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * SWEA 4014 - 활주로 건설
 * 주어지는 값
 * T : 테스트 케이스 수
 * N : 맵의 크기
 * X : 경사로 길이
 * 2번째 줄부터 N번쨰 줄 : 맴의 지형 높이
 * 
 * 
 * N * N 크기의 절벽지대에 활주로를 건설
 * map의 숫자는 지형의 높이
 * 경사로는 길이가 X이고, 높이는 1이다
 * 세로나 가로에 설치할 수 있는 경우의 수를 출력하라
 * 
 * 1. 가로 세로를 따로 체크
 * 2. 아래로 한번 체크하고 위로 한번 체크 (단, 동시에 설치되는 곳은 없어야 하므로 boolean 으로 체크)
 * 3. 과거값과 현재값이 같으면 cnt++, 그 다음 값이 다르면 + 1 인지 체크
 * 4. 체크해서 True면 ans++;
 * 
*/
public class Solution {
	static int N, X;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t <= T; t++) {
			//입력
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			int[][] rowMap = new int[N][N];
			for(int i=0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					rowMap[j][i] = map[i][j];
				} 
			}
			
			int ans = 0;
			
			ans += simulate(map);
			ans += simulate(rowMap);
			
			//출력
			bw.write("#" + t + " " + ans + "\n");
		}
		bw.close();
	}
	
	private static int simulate(int[][] map) {
		int result = 0;
		for(int i=0; i < N; i++) {
			boolean flag = true;
			int cnt = 1;
			for(int j=1; j < N; j++) {
				if(map[i][j - 1] != map[i][j]) {
					if(map[i][j - 1] == map[i][j] + 1) {
						cnt = 0;
						if(check(map, i, j, map[i][j])) {
							j += X - 1;
						} else {
							flag = false;
							break;
						}
					} else if(map[i][j - 1] == map[i][j] - 1) {
						if(cnt < X ) {
							flag = false;
							cnt = 1;
							break;
						}
						cnt = 1;
					} else {
						flag = false;
						cnt = 1;
						break;
					}
					
				} else cnt++;
			}
			
			if(flag) result++;
		}
		
		return result;
	}

	private static boolean check(int[][] map, int r, int c, int height) {
		int k = 1;
		while(c + k < N && map[r][c + k] == height && k < X) k++;
		if(k == X) return true;
		else return false;
	}
	
}
