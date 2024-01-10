import java.awt.Point;
import java.util.Scanner;

/*
 * 백준 1063 - 킹
 * 8 x 8 크기의 체스판에 킹이 1개 있음.
 * 킹의 현재 위치가 주어짐.
 * 알파벳 -> 열을 나타냄 ( A - H )
 * 숫자 -> 행을 나타냄 ( 1 - 8 )
 * 행은 가장 아래가 1, 열은 가장 왼쪽이 A
 * R -> 오른쪽, L -> 왼쪽, B -> 아래로, T -> 위로
 * RT -> 오른쪽 위, LT -> 왼쪽 위, RB -> 오른쪽 아래, LB -> 왼쪽 아래
 * */

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String king = sc.next();
		String stone = sc.next();
		Point kingPos = new Point(king.charAt(1) - '0', king.charAt(0) - 'A' + 1);
		Point stonePos = new Point(stone.charAt(1) - '0', stone.charAt(0) - 'A' + 1);
		int N = sc.nextInt();
		//상 우 하 좌 좌상 우상 우하 좌하
		int[] dr1 = {1, 0, -1, 0, 1, 1, -1, -1};
		int[] dc1 = {0, 1, 0, -1, -1, 1, 1, -1};
		for(int i=0; i < N; i++) {
			int d = 0;
			switch(sc.next()) {
				case "R" :
					d = 1;
					break;
				case "B" :
					d = 2;
					break;
				case "L" :
					d = 3;
					break;
				case "LT" :
					d = 4;
					break;
				case "RT" :
					d = 5;
					break;
				case "RB" :
					d = 6;
					break;
				case "LB" :
					d = 7;
					break;
			}
			int nr = kingPos.x + dr1[d];
			int nc = kingPos.y + dc1[d];
			if(!outMap(nr, nc)) {
				if(nr == stonePos.x && nc == stonePos.y) {
					int stonenr = stonePos.x + dr1[d];
					int stonenc = stonePos.y + dc1[d];
					if(outMap(stonenr, stonenc)) continue;
					stonePos.x = stonenr;
					stonePos.y = stonenc;
				}
				kingPos.x = nr;
				kingPos.y = nc;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toString((char) kingPos.y + 'A' - 1) + kingPos.x + "\n");
		sb.append(Character.toString((char) stonePos.y + 'A' - 1) + stonePos.x + "\n");
		
		System.out.println(sb.toString());
		sc.close();
	}
	
	private static boolean outMap(int r, int c) {
		return ( r < 1 || r > 8 || c < 1 || c > 8);
	}
}