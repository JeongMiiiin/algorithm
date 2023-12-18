import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for(int i=0; i < N; i++) {
			int j = 0;
			for(char c : br.readLine().toCharArray()) map[i][j++] = c;
		}
		int result = 0;
		for(int i=0; i < N; i++) {
			for(int j=0; j < N; j++) {
				int cur = map[i][j];
				//가로 체크
				int chance = 0, cnt = 1, addIdx = j + 1;
				row : while(addIdx < N) {
					if(cur != map[i][addIdx]) {
						if(chance > 0) break row; //이미 찬스를 쓴 경우
						else {
							if(i - 1 >= 0 && cur == map[i - 1][addIdx]) chance++;
							else if(i + 1 < N && cur == map[i + 1][addIdx]) chance++;
							else {
								if(addIdx + 1 < N && cur == map[i][addIdx + 1]) result = Math.max(result, cnt + 1);
								break row;
							}
						}
					}
					addIdx++;
					cnt++;
				}
				result = Math.max(result, cnt);
				//세로 체크
				chance = 0;
				cnt = 1;
				addIdx = i + 1;
				col : while(addIdx < N) {
					if(cur != map[addIdx][j]) {
						if(chance > 0) break col; //이미 찬스를 쓴 경우
						else {
							if(j - 1 >= 0 && cur == map[addIdx][j - 1]) chance++;
							else if(j + 1 < N && cur == map[addIdx][j + 1]) chance++;
							else {
								if(addIdx + 1 < N && cur == map[addIdx + 1][j]) result = Math.max(result, cnt + 1);
								break col;
							}
						}
					}
					addIdx++;
					cnt++;
				}
				result = Math.max(result, cnt);
			}
		}
		
		//반대로 체크
		for(int i= N - 1; i > -1; i--) {
			for(int j= N - 1; j > -1; j--) {
				int cur = map[i][j];
				//가로 체크
				int chance = 0, cnt = 1, subIdx = j - 1;
				row : while(subIdx > -1) {
					if(cur != map[i][subIdx]) {
						if(chance > 0) break row; //이미 찬스를 쓴 경우
						else {
							if(i - 1 >= 0 && cur == map[i - 1][subIdx]) chance++;
							else if(i + 1 < N && cur == map[i + 1][subIdx]) chance++;
							else {
								if(subIdx - 1 > -1 && cur == map[i][subIdx - 1]) result = Math.max(result, cnt + 1);
								break row;
							}
						}
					}
					subIdx--;
					cnt++;
				}
				result = Math.max(result, cnt);
				//세로 체크
				chance = 0;
				cnt = 1;
				subIdx = i - 1;
				col : while(subIdx > -1) {
					if(cur != map[subIdx][j]) {
						if(chance > 0) break col; //이미 찬스를 쓴 경우
						else {
							if(j - 1 >= 0 && cur == map[subIdx][j - 1]) chance++;
							else if(j + 1 < N && cur == map[subIdx][j + 1]) chance++;
							else {
								if(subIdx - 1 > -1 && cur == map[subIdx - 1][j]) result = Math.max(result, cnt + 1);
								break col;
							}
						}
					}
					subIdx--;
					cnt++;
				}
				result = Math.max(result, cnt);
			}
		}
		
		System.out.println(result);
		br.close();
	}
}