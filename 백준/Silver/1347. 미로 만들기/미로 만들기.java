import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		int top = 0, bottom = 0, left = 0, right = 0;
		int curR = 0, curC = 0, d = 2;
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		for(char c : sc.nextLine().toCharArray()) {
			if(c == 'F') {
				curR += dr1[d];
				curC += dc1[d];
				top = Math.min(curR, top);
				bottom = Math.max(curR, bottom);
				left = Math.min(curC, left);
				right = Math.max(curC, right);
				q.add(new Point(curR, curC));
			} else if(c == 'R'){
				if(++d > 3) d = 0;
			} else {
				if(--d < 0) d = 3;
			}
		}
		char[][] map = new char[bottom - top + 1][right - left + 1];
		for(int i=0; i < map.length; i++) Arrays.fill(map[i], '#');
		//
		while(!q.isEmpty()) {
			Point cur = q.poll();
			map[cur.x - top][cur.y - left] = '.';
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < map.length; i++) {
			for(char c : map[i]) sb.append(c);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}