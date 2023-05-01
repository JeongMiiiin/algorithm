/*
 * 프로그래머스 - 자물쇠와 열쇠
 * 
 * 특이한 형태의 자물쇠가 있음.
 * 잠겨있는 자물쇠는 N x N 크기
 * 특이한 모양의 열쇠는 N x M 크기
 * 자물쇠에는 홈이 파여 있고, 열쇠 또한 홈과 돌기 부분이 있음.
 * 열쇠는 회전과 이동이 가능하며 열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 열리게 되는 구조.
 * 자물쇠의 영역에 벗어난 부분의 열쇠의 홈과 돌기는 자물쇠에 영향을 주지 않음.
 * 단, 자물쇠 영역 내에서는 정확히 일치해야 함
 * 열쇠로 자물쇠를 열 수 있으면 true, 열 수 없으면 false 리턴
 * 
 * 만들어야 할 것
 * 1. 회전함수 - 완료
 * 2. 비교함수
 * 
*/

class Solution {
    static int N, M, holeCnt;
    public boolean solution(int[][] key, int[][] lock) {
        //초기값 설정
		N = lock.length;
		M = key.length;
		holeCnt = 0;
		
		for(int i=0; i < N; i++) for(int j=0; j < N; j++) if(lock[i][j] == 0) holeCnt++;
		
		//회전하지 않은 채로 비교해서 맞으면 리턴
		if(matching(-M, -M, key, lock)) return true;
		
		int[][] turn90Table = new int[M][M];
		turnMap90(key, turn90Table);
		if(matching(-M, -M, turn90Table, lock)) return true;
		
		int[][] turn180Table = new int[M][M];
		turnMap90(turn90Table, turn180Table);
		if(matching(-M, -M, turn180Table, lock)) return true;
		
		int[][] turn270Table = new int[M][M];
		turnMap90(turn180Table, turn270Table);
		if(matching(-M, -M, turn270Table, lock)) return true;
        
        return false;
    }
	
	private static boolean matching(int startR, int startC, int[][] key, int[][] lock) {
		if(startC >= N) {
			startR++;
			startC = -M;
		}
		
		if(startR >= N) return false;
		
		boolean flag = true;
		int matchingCnt = 0;
		outer : for(int i=startR, keyR = 0; i < N; i++, keyR++) {
				if(i < 0 || keyR >= M) continue;
			for(int j=startC, keyC = 0; j < N; j++, keyC++) {
				if(j < 0 || keyC >= M) continue;
                
				//홈과 홈이 만나거나 돌기와 돌기가 만날 때
				if(key[keyR][keyC] == lock[i][j]) {
					flag = false;
					break outer;
				}
				//열쇠는 돌기일 때 (자물쇠는 홈일 때)
				if(key[keyR][keyC] == 1) matchingCnt++;
			}
		}
		
		if(matchingCnt == holeCnt && flag) return true;
		
		//다음 순서로 보내기
		return matching(startR, startC + 1, key, lock);
		
	}

	//맵 90도 돌리기
	private static void turnMap90(int[][] originTable, int[][] copyTable) {
		for(int i=0; i < M; i++) for(int j=0; j < M; j++) copyTable[M - 1 - j][i] = originTable[i][j];
	}
}