import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int x = sc.nextInt();
		int y = sc.nextInt();
		int w = sc.nextInt();
		int h = sc.nextInt();
		int[][] map = new int[w + 1][h + 1];
		
		for(int r=0; r <= w; r++) {
			if(r > 0 && r < w) {
				map[r][0] = 1;
				map[r][h] = 1;
			} else {
				for(int c=0; c <= h; c++) {
					map[r][c] = 1;
				}
			}
		}
		
		if(map[x][y] == 1) {
			System.out.println(0);
			return;
		}
		int min = Integer.MAX_VALUE;
		//좌 좌상 상 우상 우 우하 하 좌하
		int[] dr1 = {0,-1,-1,-1,0,1,1,1};
		int[] dc1 = {-1,-1,0,1,1,1,0,-1};
		for(int i=0; i < 8; i++) {
			int cnt = 1;
			int r = x + dr1[i];
			int c = y + dc1[i];
			while(true) {
				if(map[r][c] == 1) break;
				r += dr1[i];
				c += dc1[i];
				cnt++;
			}
			min = Math.min(min, cnt);
		}
		
		System.out.println(min);
	}
}
