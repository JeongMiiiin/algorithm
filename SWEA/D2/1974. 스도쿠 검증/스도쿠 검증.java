import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * SWEA 1974 - 스도쿠 검증
*/
public class Solution {
	static int T, N, ans;
	static int[][] map = new int[9][9];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int t=1; t <=T; t++) {
			//스도쿠 값 받기
			for(int r=0; r < 9; r++) for(int c=0; c < 9; c++) map[r][c] = sc.nextInt();
			
			ans = solve();
			
			System.out.println("#" + t + " " + ans);
		}
		sc.close();
	}
	static Set<Integer> hs = new HashSet<>();
	private static int solve() {
		//horizontal 가로
		for (int i = 0; i < 9; i++) {
			hs.clear();
			for(int j=0; j < 9; j++) hs.add(map[i][j]);
			if(hs.size() < 9) return 0; //중복된 값이 있을 떄
		}
		//vertical 세로
		for (int i = 0; i < 9; i++) {
			hs.clear();
			for(int j=0; j < 9; j++) hs.add(map[j][i]);
			if(hs.size() < 9) return 0; //중복된 값이 있을 떄
		}
		//square 사각형
		// 큰 사각형
		for (int i = 0; i < 3; i++) {
			for(int j=0; j < 3; j++) {
				hs.clear();
				//작은 사각형
				for (int k = 0; k < 3; k++) for (int l = 0; l < 3; l++) hs.add(map[i * 3 + k][j * 3 + l]);
				if(hs.size() < 9) return 0; //중복된 값이 있을 떄
			}
		}
		
		return 1;
	}
}