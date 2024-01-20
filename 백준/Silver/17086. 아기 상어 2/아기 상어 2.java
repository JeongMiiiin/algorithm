import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int R, C;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		int[][] map = new int[R][C];
		for(int i=0; i < R; i++) for(int j=0; j < C; j++) map[i][j] = sc.nextInt();
		int result = 0;
		//좌상 상 우상 우 우하 하 좌하 좌
		int[] dr1 = {-1, -1, -1, 0, 1, 1, 1, 0};
		int[] dc1 = {-1, 0, 1, 1, 1, 0, -1, -1};
		for(int i=0; i < R; i++) {
			for(int j=0; j < C; j++) {
				if(map[i][j] == 1) continue; //아기상어가 있는 곳인 경우
				boolean[][] visited = new boolean[R][C]; //방문처리 배열
				visited[i][j] = true; //방문 처리
				int temp = 0;
				Queue<Point> q = new LinkedList<>();
				q.add(new Point(i, j));
				outer : while(!q.isEmpty()) {
					int size = q.size();
					temp++;
					for(int z=0; z < size; z++) {
						Point cur = q.poll();
						for(int d=0; d < 8; d++) {
							int nr = cur.x + dr1[d];
							int nc = cur.y + dc1[d];
							if(outMap(nr, nc) || visited[nr][nc]) continue; //맵에서 벗어나거나 이미 방문한 곳인 경우
							if(map[nr][nc] == 0) {
								visited[nr][nc] = true;
								q.add(new Point(nr, nc));
							} else break outer; //상어를 만났을 때
						}
					}
				}
				
				result = Math.max(result, temp);
			}
		}
		
		System.out.println(result);
		sc.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
}