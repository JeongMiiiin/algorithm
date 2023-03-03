import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 17144번 - 미세먼지 안녕!
 * 주어지는 값
 * R : 방의 가로 크기
 * C : 방의 세로 크기
 * T : 지나는 시간
 * 
 * 
 * 공기청정기는 항상 1번열에 있고, 크기는 두 행을 차지
 * 공기 청정기가 설치되어 있지 않은 칸에는 미세먼지가 있음
 * 
 * 1초동안 일어나는 일
 * 1.미세먼지가 상하좌우로 확산됨
 * 1-1. 인접한 방향에 공기청정기가 있거나, 칸이 없으면 확산 X
 * 1-2. 확산되는 양은 5분의 1로 소수점은 제거
 * 1-3. 원래 있던 곳의 미세먼지 양은 나눠준 만큼을 제거한 양
 * 2. 공기청정기가 작동
 * 2-1. 위쪽 공기청정기의 바람은 반시계방향 순환
 * 2-2. 아래쪽 공기청정기의 바람은 시계방향 순환
 * 2-3. 바람이 불면 미세먼지는 바람의 방향대로 1칸씩 이동
 * 
 * T초가 지난 후 방에 남아있는 미세먼지의 양 출력
 * 
*/
public class Main {
	static class Dust {
		int r, c, amount;
		public Dust(int r, int c, int amount) {
			this.r = r;
			this.c = c;
			this.amount = amount;
		}
	}
	static int R, C, T;
	static int[][] airCleanerPos = new int[2][2];
	static Queue<Dust> q = new ArrayDeque<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];
		
		//세팅
		int airCleanerCnt = 0;
		for(int i=0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					airCleanerPos[airCleanerCnt][0] = i;
					airCleanerPos[airCleanerCnt][1] = j;
					airCleanerCnt++;
				} else if(map[i][j] > 0) {
					q.offer(new Dust(i,j, map[i][j]));
				}
			}
		}
		
		simulate(0, map);
		
		int ans = 0;
		while( !q.isEmpty() ) ans += q.poll().amount;
		
		
		//출력
		System.out.println(ans);
		
	}
	private static void simulate(int seconds, int[][] map) {
		if(seconds == T) return;
		
		int[][] newMap = new int[R][C];
		
		//상 우 하 좌
		int[] dr1 = {-1,0,1,0};
		int[] dc1 = {0,1,0,-1};
		
		// 미세먼지 확산
		Dust cur;
		while( !q.isEmpty()) {
			cur = q.poll();
			int nr, nc, amount = cur.amount, divideCnt = 0;
			for(int d=0; d < 4; d++) {
				nr = cur.r + dr1[d];
				nc = cur.c + dc1[d];
				if(outMap(nr, nc)) continue; //배열을 벗어난 경우
				if(map[nr][nc] == -1) continue; //공기청정기인 경우
				divideCnt++;
				newMap[nr][nc] += amount / 5;
			}
			newMap[cur.r][cur.c] -= divideCnt * (amount / 5);
		}
		
		plusMap(map, newMap);
		
		newMap = new int[R][C];
		
		//공기순환
		int r1 = airCleanerPos[0][0];
		int r2 = airCleanerPos[1][0];
		
		//오른쪽 보내기
		int idx = 0;
		while(++idx < C - 1) {
			newMap[r1][idx + 1] = map[r1][idx];
			newMap[r2][idx + 1] = map[r2][idx];
		}
		
		//위쪽 아래쪽 보내기
		idx = -1;
		while(idx++ < R - 1) {
			if(r1 - idx > 0) newMap[r1 - idx - 1][C - 1] = map[r1 - idx][C - 1];
			if(r2 + idx < R - 1) newMap[r2 + idx + 1][C - 1] = map[r2 + idx][C - 1];
			if(r1 - idx <= 0 && r2 + idx >= R - 1) break;
		}
		
		//왼쪽 보내기
		idx = 0;
		while(++idx < C) {
			newMap[0][C - idx - 1] = map[0][C - idx];
			newMap[R - 1][C - idx - 1] = map[R - 1][C - idx];
		}
		
		//아래쪽 보내기
		idx = 0;
		while(++idx < R - 1) {
			if(idx < r1) if(map[idx][0] > -1) newMap[idx][0] = map[idx - 1][0];
			if(R - idx > r2 + 1) if(map[R - idx][0] > -1) newMap[R - idx - 1][0] = map[R - idx][0];
			if(idx >= r1 && R - idx <= r2 + 1) break;
		}
		
		newMap[r1][0] = -1;
		newMap[r2][0] = -1;
		
		for(int r=1; r < r1; r++) for(int c=1; c < C - 1; c++) newMap[r][c] = map[r][c];
		
		for(int r= r2 + 1; r < R - 1; r++) for(int c=1; c < C - 1; c++) newMap[r][c] = map[r][c];
		
		for(int r=0; r < R; r++) for(int c=0; c < C; c++) if(newMap[r][c] > 0) q.offer(new Dust(r,c, newMap[r][c]));
		
		simulate(seconds + 1, newMap);
	}
	
	private static void plusMap(int[][] map, int[][] newMap) {
		for(int r=0; r < R; r++) for(int c=0; c < C; c++) map[r][c] += newMap[r][c];
	}
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
	
	
	
}
