import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * SWEA 5644번 - 무선충전
 * 스마트폰 무선 충전 시 최적의 BC(배터리 충전기)를 선택하는 알고리즘
 * 충전 범위의 거리는 |x1 - x2| + |y1 - y2|에 속하는 거리만큼
 * 충전범위가 겹치는 곳은 둘 중 하나를 선택해 충전 가능
 * 하나의 BC를 동시에 사용할 경우 사용자 수만큼 충전 양을 균등하게 분배
 * 사용자의 이동 궤적이 주어졌을 때 모든 사용자가 충전한 양의 최대값을 출력하라
 * 사용자 A는 지도의 (1,1) 지점에서
 * 사용자 B는 지도의 (10,10) 지점에서 출발
 * 사용자 이동은 1초에 한번씩
 * 0 : 이동하지 않음
 * 1 : 상으로
 * 2 : 우로
 * 3 : 하로
 * 4 : 좌로
 * BC 정보
 * (X,Y) : 위치좌표 C : 충전범위 P : 충전양
 * 사용자는 초기 위치부터 충전 가능
 * -- dfs로 모든 경우 탐색을 하며, 최대값 도출해야 할듯
 * -- 공유하고 있는 곳은 어떻게 표시? -> boolean?
*/
public class Solution {
	static int M, A, ans;
	static int[][][] map; //지도를 담는 배열
	static int[] userAPos, userBPos, userAWalk, userBWalk;
	static int[] dr = {0,-1,0,1,0};
	static int[] dc = {0,0,1,0,-1};
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			//입력
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			map = new int[11][11][A + 1];
			ans = 0;
			//사용자 이동 정보 담기
			userAPos = new int[] {1,1}; //사용자1
			userAWalk = new int[M];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i < M; i++) userAWalk[i] = Integer.parseInt(st.nextToken());
			userBPos = new int[] {10,10}; //사용자2
			userBWalk = new int[M];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i < M; i++) userBWalk[i] = Integer.parseInt(st.nextToken());
			
			//충전범위 설정
			settingBC(1);
			
			dfs(0, 0);
			
			//출력
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void dfs(int cnt, int sum) {
		//값 찾아내기
		int userAx = userAPos[0];
		int userAy = userAPos[1];
		int userBx = userBPos[0];
		int userBy = userBPos[1];
		
		int[] userAMax = findMaxBC(userAx, userAy);
		int[] userBMax = findMaxBC(userBx, userBy);
		
		if(userAMax[0] != userBMax[0]) { //각자 얻는 최대값이 다른 경우 -> 공유하지 않아도 될때
			sum += map[userAx][userAy][userAMax[0]] + map[userBx][userBy][userBMax[0]];
		} else { //최대값을 공유하고 있을 때
			//둘다 다른 대안이 없을 때
			if(userAMax[1] == 0 && userBMax[1] == 0) sum += map[userAx][userAy][userAMax[0]];
			//B만 대안이 없을 떄
			else if(userBMax[1] == 0) sum += map[userAx][userAy][userAMax[1]] + map[userBx][userBy][userBMax[0]];
			//A만 대안이 없을 때
			else if(userAMax[1] == 0) sum += map[userAx][userAy][userAMax[0]] + map[userBx][userBy][userBMax[1]];
			else { //둘다 대안이 있는 경우
				int max = Math.max(map[userAx][userAy][userAMax[1]], map[userBx][userBy][userBMax[1]]); //가장 괜찮은 값 더해주기
				sum += map[userAx][userAy][userAMax[0]] + max;
			}
		}
		
		if(cnt == M) {
			ans = Math.max(ans, sum);
			return;
		}
		
		//사용자1 이동
		int userAAction = userAWalk[cnt];
		
		userAPos[0] += dr[userAAction];
		userAPos[1] += dc[userAAction];
		//사용자1이 배열에서 벗어난 경우 원복
		if(userAPos[0] < 1 || userAPos[0] > 10 || userAPos[1] < 1 || userAPos[1] > 10) {
			userAPos[0] -= dr[userAAction];
			userAPos[1] -= dc[userAAction];
		}
		
		//사용자2 이동
		int userBAction = userBWalk[cnt];
				
		userBPos[0] += dr[userBAction];
		userBPos[1] += dc[userBAction];
		//사용자1이 배열에서 벗어난 경우 원복
		if(userBPos[0] < 1 || userBPos[0] > 10 || userBPos[1] < 1 || userBPos[1] > 10) {
			userBPos[0] -= dr[userBAction];
			userBPos[1] -= dc[userBAction];
		}
		
		dfs(cnt + 1, sum);
	}

	private static void settingBC(int cnt) throws Exception {
		if(cnt == A + 1) return;
		//충전기 정보 받기
		st = new StringTokenizer(br.readLine()); 
		int x = Integer.parseInt(st.nextToken()); //x좌표
		int y = Integer.parseInt(st.nextToken()); //y좌표
		int C = Integer.parseInt(st.nextToken()); //충전범위
		int P = Integer.parseInt(st.nextToken()); //충전양
		//충전범위 세팅
		int nx, ny;
		for(int i=0; i < Math.pow((C * 2 + 1), 2); i++) {
			nx = x - (C - (i % (C * 2 + 1)));
			ny = y - (C - (i / (C * 2 + 1)));
			if(nx < 1 || nx > 10 || ny < 1 || ny > 10) continue; //배열의 값을 벗어날 경우
			if(Math.abs(x - nx) + Math.abs(y - ny) > C) continue; //충전 범위를 벗어날 경우
			map[ny][nx][cnt] = P;
		}
		
		settingBC(cnt + 1);
	}
	
	//현재 위치에서 가장 최대값 두개의 idx를 알려주는 함수 (사용자는 두명이기에)
	private static int[] findMaxBC(int r, int c) {
		int[] temp = Arrays.copyOf(map[r][c], A + 1);
		Arrays.sort(temp);
		int[] result = new int[2];
		int max = temp[A];
		if(max > 0) {
			int secondMax = temp[A - 1];
			for(int i=0; i < A + 1; i++) {
				if(map[r][c][i] == max) result[0] = i;
				else if(map[r][c][i] > 0 && map[r][c][i] == secondMax) result[1] = i;
			}
		}
		return result;
	}
	
}
