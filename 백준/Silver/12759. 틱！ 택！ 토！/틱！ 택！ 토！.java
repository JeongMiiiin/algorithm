import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] map = new int[3][3];
		int start = Integer.parseInt(sc.nextLine());
		int status = 0;
		while(sc.hasNext()) {
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = start;
			//가로 체크
			boolean flag = true;
			for(int i=0; i < 3; i++) if(map[r][i] != start) flag = false;
			
			if(flag) {
				status = start;
				break;
			}
			
			flag = true;
			//세로 체크
			for(int i=0; i < 3; i++) if(map[i][c] != start) flag = false;
			
			if(flag) {
				status = start;
				break;
			}
			
			//대각선 체크
			flag = true;
			for(int i=0; i < 3; i++) if(map[i][i] != start) flag = false;
			
			if(flag) {
				status = start;
				break;
			}
			
			flag = true;
			for(int i=0; i < 3; i++) if(map[i][2 - i] != start) flag = false;
			
			if(flag) {
				status = start;
				break;
			}
			
			
			if(++start > 2) start = 1;
		}
		System.out.println(status);
		sc.close();
	}
}