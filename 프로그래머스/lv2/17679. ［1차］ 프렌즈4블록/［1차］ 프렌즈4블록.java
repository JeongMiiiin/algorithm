import java.util.Arrays;
import java.util.Stack;

/*
 * 프로그래머스 - 프렌즈4블록
 * 
 * 같은 모양의 블록이 2 x 2 형태로 4개가 붙어있을 경우 사라지면서 점수를 얻는 게임.
 * 블록이 지워진 후에는 위에 있는 블록이 아래로 떨어져 빈 공간을 채우게 됨.
 * 
 * 첫 배치가 주어졌을 때, 지워지는 블록은 모두 몇 개인지 판단하라
*/

class Solution {
    public int solution(int m, int n, String[] board) {
        char[][] map = new char[m][n];
		
		for(int i=0; i < m; i++) {
			char[] cList = board[i].toCharArray();
			for(int j=0; j < n; j++) map[i][j] = cList[j];
		}
		
		int answer = 0;
		boolean check = false;
		boolean[][] selected = new boolean[m][n];
		boolean[] checkCol = new boolean[n];
		
		//우 우하 하
		int[] dr1 = {0, 1, 1};
		int[] dc1 = {1, 1, 0};
		
		do {
			//초기화
			check = false;
			for(int i=0; i < m; i++) Arrays.fill(selected[i], false);
			Arrays.fill(checkCol, false);
			
			for(int i=0; i < m - 1; i++) {
				for(int j=0; j < n - 1; j++) {
					char target = map[i][j]; //확인할 블록
					if(target == '0') continue;
					boolean flag = true;
					for(int d=0; d < 3; d++) {
						int nr = i + dr1[d];
						int nc = j + dc1[d];
						//맵에서 벗어나거나 다른 블록을 만나면 안되는것으로 판단하고 탐색 패스
						if(nr < 0 || nr >= m || nc < 0 || nc >= n || map[nr][nc] != target) { 
							flag = false;
							break;
						}
					}
					
					//삭제해야하는 블록일 때
					if(flag) {
						selected[i][j] = true;
						for(int d=0; d < 3; d++) {
							int nr = i + dr1[d];
							int nc = j + dc1[d];
							selected[nr][nc] = true;
						}
					}
				}
			}
			
			for(int i=0; i < m; i++) {
				for(int j=0; j < n; j++) {
					if(selected[i][j]) { //삭제해야하는 블록일 때
						map[i][j] = '0';
						check = true;
						checkCol[j] = true;
						answer++;
					}
				}
			}
			
			Stack<Character> temp = new Stack<>();
			for(int i=0; i < n; i++) {
				if(checkCol[i]) { //삭제한 블록이 있는 열인 경우
					temp.clear();
					for(int j= m - 1; j >= 0; j--) if(map[j][i] != '0') temp.add(map[j][i]);
					while(temp.size() < m) temp.add('0');
					for(int j= 0; j < m; j++) map[j][i] = temp.pop();
				}
			}
			
		} while(check);
		
        
        return answer;
    }
}