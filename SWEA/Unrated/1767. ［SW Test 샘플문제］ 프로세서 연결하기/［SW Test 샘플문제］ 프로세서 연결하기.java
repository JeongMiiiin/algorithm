import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * SWEA 1767 - 프로세서 연결하기
 * 주어지는 값
 * N : 가로와 세로 크기
 * 맵 : 1은 core
 * 1개의 cell에는 1개의 core 혹은 1개의 전선이 올 수 있음.
 * 둘레에는 전원이 흐르고 있음
 * 코어와 연결하는 전선은 직선으로만 설치 가능 (교차 불가능)
 * 가장자리에 위치한 core는 이미 전원이 연결된 것
 * 
 * 결국 가장자리에 위치한 core를 제외하고는 
*/
public class Solution {
	static class Core {
		int r,c;
		boolean edge;

		public Core(int r, int c, boolean edge) {
			this.r = r;
			this.c = c;
			this.edge = edge;
		}
	}
	static int N, ans, ansCnt, maxCnt;
	static List<Core> cores;
	static int[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			ans = Integer.MAX_VALUE;
            ansCnt = 0;
			cores = new ArrayList<>();
			//입력
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i=0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						if(i == 0 || i == N - 1 || j == 0 || j == N - 1) cores.add(new Core(i, j, true));
						else cores.add(new Core(i, j, false));
					}
				}
			}
			
			maxCnt = cores.size();
			
			boolean[][] checkMap = new boolean[N][N];
			
			connectLightening(0, 0, 0, checkMap);
			
			//출력
			bw.write("#" + t + " " + ans + "\n");
		}
		
		bw.close();
	}
	
	//상 우 하 좌
	static int[] dr1 = {-1,0,1,0};
	static int[] dc1 = {0,1,0,-1};
	private static void connectLightening(int checkNum, int sum, int successCnt, boolean[][] tempMap) {
		if(ansCnt > maxCnt - checkNum + successCnt) return;
		
		if(checkNum == maxCnt) {
			if(successCnt > ansCnt) {
				ansCnt = successCnt;
				ans = sum;
			} else if(ansCnt == successCnt) ans = Math.min(ans, sum);
			return;
		}
		Core core = cores.get(checkNum);
		if( !core.edge ) { //가장자리에 위치하고 있지 않은 경우
			int tempSum = 0;
			boolean[][] checkMap;
			for(int d=0; d < 4; d++) {
				checkMap = new boolean[N][N];
				for(int i=0; i < N; i++) checkMap[i] = Arrays.copyOf(tempMap[i], N);
				tempSum = check(core.r, core.c, d, checkMap);
				if(tempSum != 0) connectLightening(checkNum + 1, sum + tempSum, successCnt + 1, checkMap);
				else connectLightening(checkNum + 1, sum + tempSum, successCnt, checkMap);
			}
			checkMap = new boolean[N][N];
			for(int i=0; i < N; i++) checkMap[i] = Arrays.copyOf(tempMap[i], N);
			connectLightening(checkNum + 1, sum + tempSum, successCnt, checkMap);
		} else {
			connectLightening(checkNum + 1, sum, successCnt + 1, tempMap);
			connectLightening(checkNum + 1, sum, successCnt, tempMap);
		}
		
		
	}

	//해당방향으로 찾아보는 함수
	private static int check(int r, int c, int d, boolean[][] checkMap) {
		int result = 0;
		int nr = r, nc = c;
		boolean flag = true;
		while(nr >= 0 && nr < N && nc >= 0 && nc < N) {
			nr += dr1[d];
			nc += dc1[d];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;
			if(map[nr][nc] == 1 || checkMap[nr][nc]) {
				flag = false;
				break;
			} else {
				checkMap[nr][nc] = true;
				result++;
			}
		}
		
		if(flag) {
			for(int i=0; i < result; i++) {
				nr -= dr1[d];
				nc -= dc1[d];
				checkMap[nr][nc] = true;
			}
		} else result = 0;
		
		return result;
	}
	
}
