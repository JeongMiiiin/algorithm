import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 백준 10026번 - 적록색약
*/
public class Main {
	static int N;
	static int[][] map;
	static int[] normalCnt = new int[3], specialCnt = new int[2];
	static boolean[] check = new boolean[3];
	static boolean[][] normalMap, specialMap;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		normalMap = new boolean[N][N];
		specialMap = new boolean[N][N];
		
		for(int i=0; i < N; i++) {
			String s = br.readLine();
			for(int j=0; j < N; j++) {
				if(s.charAt(j) == 'B') map[i][j] = 1;
				else if(s.charAt(j) == 'G') map[i][j] = 2;
			}
		}
		
		for(int i=0; i < N; i++) for(int j=0; j < N; j++) if( !normalMap[i][j] || !specialMap[i][j]) {
			if(!normalMap[i][j]) {
				normalCnt[map[i][j]]++;
				dfs(i,j, map[i][j]);
			} 
			if(!specialMap[i][j]) {
				if(map[i][j] == 1) specialCnt[map[i][j]]++;
				else specialCnt[map[i][j] % 2]++;
				dfs2(i,j, map[i][j]);
			}
			
		}
		int idx = 0, normal = 0, special = 0;
		while(idx < 3) {
			normal += normalCnt[idx];
			if(idx < 2) special += specialCnt[idx];
			idx++;
		}
		System.out.println(normal + " " + special);
	}
	
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1}; 
	private static void dfs(int r, int c, int findNum) {
		if(map[r][c] != findNum) { 
			return; //틀린 경우
		}
		normalMap[r][c] = true;
		for(int i=0; i < 4; i++) {
			int nr = r + dr1[i];
			int nc = c + dc1[i];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue; //배열을 벗어난 경우
			if(!normalMap[nr][nc]) dfs(nr, nc, findNum);
		}
			
	}
	
	
	private static void dfs2(int r, int c, int findNum) {
		if(map[r][c] != findNum) { 
			if(map[r][c] == 1) return;
			else if(map[r][c] % 2 != findNum % 2) return;
		}
		specialMap[r][c] = true;
		for(int i=0; i < 4; i++) {
			int nr = r + dr1[i];
			int nc = c + dc1[i];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue; //배열을 벗어난 경우
			if(!specialMap[nr][nc]) dfs2(nr, nc, findNum);
		}	
	}
}
