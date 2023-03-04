import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * SWEA 4013 - 특이한 자석
 * 주어지는 값
 * T : 테스트 케이스 수
 * K : 회전을 해야하는 연산 횟수
 * 4개의 줄 : 각각 8개의 날의 자성정보가 주어짐
 * N극 : 0, S극 : 1
 * 자성정보는 빨간색 화살표 위치의 날부터 시계방향 순서대로 주어짐
 * 회전연산의 종류
 * 첫번째 1 : 시계방향, -1 : 반시계방향
 * 두번째 숫자 : 돌려야 하는 자석
 * 4개의 자석
 * 각 자석마다 8개의 날
 * 각 날에는 N극과 S극의 자성
 * 하나의 자석이 1칸 회전할 때마다, 인접한 자석의 접한 날의 자성이 다를 경우
 * 인접한 자석은 반대 방향으로 1칸 회전.
 * 모든 회전이 끝난 후 점수 계산
 * 1번 자석의 첫번째 위치에 자성이 N : 0, S : 1
 * 2번 자석의 첫번째 위치에 자성이 N : 0, S : 2
 * 3번 자석의 첫번째 위치에 자성이 N : 0, S : 4
 * 4번 자석의 첫번째 위치에 자성이 N : 0, S : 8
 * 모든 자석의 회전이 끝난 후 획득한 점수의 총 합을 출력
 * 1. 각각의 자성정보 담기 (map?) O
 * 2. 인접한 곳의 자성을 따로 담아주기 int[3][2] 1. 1번째 자석과 2번째 자석이 인접한 부분, 2. 2번째 자석과 3번째 자석이 인접한 부분, 3. 3번째 자석과 4번째 자석이 인접한 부분 -> 필요 없어짐
 * 3. 회전 연산에 대한 값 담아주기 int[K][2]
 * 4. 돌리기 전 인접한 자성 확인
 * 5. 돌리고 인접한 자성이 반대일 경우 반대 자석은 반대로 돌려주기
 * 6. 마지막 회전 후 첫번째 위치한 값들 뽑아내기 Math.abs(n - 1) * Math.pow(2, i)
*/
public class Solution {
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int K = Integer.parseInt(br.readLine());
			
			map = new int[4][8];
			for(int i=0; i < 4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j < 8; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int[][] cmd = new int[K][2];
			for(int i=0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				cmd[i][0] = Integer.parseInt(st.nextToken()) - 1;
				cmd[i][1] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i < K; i++) goAction(cmd[i][0], cmd[i][1], -1);
			
			int ans = 0;
			for(int i=0; i < 4; i++) ans += map[i][0] * Math.pow(2, i);
			
			//출력
			System.out.println("#" + t + " " + ans);
		}
	}
	private static void goAction(int target, int dir, int prev) {
		int reverse = dir * -1;
		
		//최초 회전하는 자석인 경우
		if(prev == -1) {
			if(target != 0) if(map[target][6] != map[target - 1][2]) goAction(target - 1, reverse, target); //같은 자성을 가지고 있을 때
			if(target != 3) if(map[target][2] != map[target + 1][6]) goAction(target + 1, reverse, target); //같은 자성을 가지고 있을 때
		} else { //영향을 받아 도는 경우 해당 방향은 회전 안하게
			if(target != 0 && target - 1 != prev) if(map[target][6] != map[target - 1][2]) goAction(target - 1, reverse, target); //같은 자성을 가지고 있을 때
			if(target != 3 && target + 1 != prev) if(map[target][2] != map[target + 1][6]) goAction(target + 1, reverse, target); //같은 자성을 가지고 있을 때
		}
		
		rotate(target, dir);
		
	}
	
	private static void rotate(int target, int dir) {
		Queue<Integer> q = new ArrayDeque<>();
		if(dir == 1) {
			for(int i=0; i < 8; i++) q.offer(map[target][i]);
			for(int i=1; i < 8; i++) map[target][i] = q.poll();
			map[target][0] = q.poll();
		} else {
			for(int i=7; i >= 0; i--) q.offer(map[target][i]);
			for(int i=6; i >= 0; i--) map[target][i] = q.poll();
			map[target][7] = q.poll();
		}
	}
}
