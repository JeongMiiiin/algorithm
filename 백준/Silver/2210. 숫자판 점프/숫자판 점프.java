import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static int standard = 6;
	static int[][] map;
	static Set<String> set;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[5][5];
		for(int i=0; i < 5; i++) for(int j=0; j < 5; j++) map[i][j] = sc.nextInt();
		
		set = new HashSet<>();
		for(int i=0; i < 5; i++) {
			for(int j=0; j < 5; j++) {
				int[] init = new int[standard];
				dfs(init, i, j, 0);
			}
		}
		System.out.println(set.size());
		sc.close();
	}
	
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	
	private static void dfs(int[] number, int r, int c, int cnt) {
		if(cnt == standard) { //6자리가 다 만들어졌을 때 set에 담고 종료
			StringBuilder sb = new StringBuilder();
			for(int i=0; i < 6; i++) sb.append(number[i]);
			set.add(sb.toString());
			return;
		}
		
		for(int d=0; d < 4; d++) {
			int nr = r + dr1[d];
			int nc = c + dc1[d];
			if(!outMap(nr, nc)) {
				int[] temp = Arrays.copyOf(number, standard);
				temp[cnt] = map[nr][nc];
				dfs(temp, nr, nc, cnt + 1);
			}
		}
		
	}
	
	private static boolean outMap(int r, int c) {
		return ( r < 0 || r >= 5 || c < 0 || c >= 5);
	}
}