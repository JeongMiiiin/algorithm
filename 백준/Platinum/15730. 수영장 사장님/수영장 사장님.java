import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 백준 15730 - 수영장 사장님
 * 물이 고일 수 있는 곳에 물을 채워넣는 방법을 찾는다.
 * PriorityQueue를 사용해 제일 낮은 높이부터 채워넣기
 * 
 * */
public class Main {
	static int R, C;

	static class Water implements Comparable<Water> {
		int r;
		int c;
		int h;

		public Water(int r, int c, int h) {
			this.r = r;
			this.c = c;
			this.h = h;
		}

		@Override
		public int compareTo(Water o) {

			return this.h - o.h;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		int[][] arr = new int[R][C];
		boolean[][] visited = new boolean[R][C];
		int result = 0;
		PriorityQueue<Water> pq = new PriorityQueue<>();

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (edgeMap(i, j)) { //테두리에 해당할 때
					pq.add(new Water(i, j, arr[i][j]));
					visited[i][j] = true;
				}
			}
		}
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};

		while (!pq.isEmpty()) {
			Water cur = pq.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr1[d];
				int nc = cur.c + dc1[d];
				if(outMap(nr,nc)|| visited[nr][nc]) continue; //맵을 벗어났거나 이미 방문한 곳인 경우
				
				if(cur.h > arr[nr][nc]) result += cur.h - arr[nr][nc]; //현재 값보다 낮은 경우 result에 더하기
				
				pq.add(new Water(nr, nc, Math.max(cur.h, arr[nr][nc]))); //제일 높은 높이로 설정하여 다시 pq에 넣기
				visited[nr][nc] = true;	

			}

		}
		System.out.println(result);
		br.close();
	}

	static boolean outMap(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

	static boolean edgeMap(int r, int c) {
		return (r < 1 || r >= R - 1 || c < 1 || c >= C - 1);
	}

}