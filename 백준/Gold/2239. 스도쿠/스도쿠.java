import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 백준 2239 - 스도쿠
 * 
 * 9 x 9 크기의 보드가 주어짐
 * 하다만 스도쿠 퍼즐이 주어졌을 때, 마저 끝내는 프로그램을 작성하라.
 * 답이 여러개 있다면, 그 중 사전식으로 가장 앞서는 것을 출력
 * 
 * 가로 채우고, 확인하고, 가로 다 채웠으면 세로 채워서 확인하고, 세로 채워서 확인하면 분할해서 확인하고 리턴
 * 
*/
public class Main {
	static int[][] map = new int[9][9];
	static List<int[]> zeroList = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//초기 맵 세팅
		for(int i=0; i < 9; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j=0; j < 9; j++) {
				map[i][j] = c[j] - '0';
				if(map[i][j] == 0) zeroList.add(new int[] {i, j}); //채워야하는 곳이면 리스트에 담아두기
			}
		}
		
		find(0);
	}
	
	static boolean flag = false;
	
	//결과찾기
	private static void find(int cnt) {
		if(flag) return;
		if(zeroList.size() == cnt) {
			flag = true;
			StringBuilder sb = new StringBuilder();
			for(int i=0; i < 9; i++) {
				for(int j=0; j < 9; j++) sb.append(map[i][j]);
				sb.append("\n");
			}
			
			System.out.println(sb.toString());
			return;
		}
		
		int r = zeroList.get(cnt)[0];
		int c = zeroList.get(cnt)[1];
		
		//방문 기록 초기화
		boolean[] selected = new boolean[10];
		
		//가로 체크
		for(int i=0; i < 9; i++) if(map[r][i] != 0) selected[map[r][i]] = true;
		
		//세로 체크
		for(int i=0; i < 9; i++) if(map[i][c] != 0) selected[map[i][c]] = true;
		
		//사각형 체크
		int startR = (r / 3) * 3;
		int startC = (c / 3) * 3;
		for(int i=startR; i < startR + 3; i++) for(int j= startC; j < startC + 3; j++) if(map[i][j] != 0) selected[map[i][j]] = true;
		
		//안 쓰인 숫자 넣기
		for(int i=1; i < 10; i++) {
			if(!selected[i]) {
				map[r][c] = i;
				find(cnt + 1);
				map[r][c] = 0;
			} 
		}
		
	}
}