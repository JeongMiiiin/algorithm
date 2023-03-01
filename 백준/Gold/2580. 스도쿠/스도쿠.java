import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * 백준 2580 - 스도쿠
 * 마디가 기저조건
*/
public class Main {
	static int T, N;
	static int[][] map = new int[9][9];
	static int ans;
	static ArrayList<Point> zeroList = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//채워야할 스도쿠 받기
		for(int i=0; i < map.length; i++) {
			for(int j=0; j < map[i].length; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 0) zeroList.add(new Point(i, j));
			} 	
		}
		
		recursive(0);
		
		sc.close();
	}
	private static void recursive(int cnt) {
		
		if(cnt == zeroList.size()) { // 기저조건
			print(map);
			System.exit(0);
		}
		
		Point p = zeroList.get(cnt);
		int r = p.x;
		int c = p.y;
		
		for(int i=1; i <= 9; i++) {
			if(check(r,c,i)) { //해당 값을 넣어도 되는지 확인
				map[r][c] = i;
				recursive(cnt + 1);
				map[r][c] = 0;
			}
		}
		
	}
	
	private static boolean check(int r, int c, int num) {
		
		//가로 && 세로
		for(int i=0; i < 9; i++) if(map[r][i] == num || map[i][c] == num) return false;
		
		//사각형
		int sr = (r / 3) * 3, sc = (c / 3) * 3; //소속된 사각형의 가로&세로 시작점
		for(int i = sr; i < sr + 3; i++) for(int j = sc; j < sc + 3; j++) if(map[i][j] == num) return false;
		
		return true;
	}
	private static void print(int[][] map) {
		for(int i=0; i < map.length; i++) {
			for(int j=0; j < map[i].length; j++) System.out.print(map[i][j] + " ");
			System.out.println();
		}
	}
}
