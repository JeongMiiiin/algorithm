import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class ShortPath {
		int arrive, distance;
		public ShortPath(int arrive, int distance) {
			this.arrive = arrive;
			this.distance = distance;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int[] dp = new int[D + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		List<ShortPath>[] info = new ArrayList[D + 1];
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(start > D || end > D) continue;
			int distance = Integer.parseInt(st.nextToken());
			if(info[start] == null) info[start] = new ArrayList<>();
			info[start].add(new ShortPath(end, distance));
		}
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if(cur.x + 1 <= D) {
				if(dp[cur.x + 1] > cur.y + 1) {
					dp[cur.x + 1] = cur.y + 1;
					q.add(new Point(cur.x + 1, cur.y + 1));
				} 
			}
			if(info[cur.x] != null) {
				for(ShortPath sp : info[cur.x]) {
					if(sp.arrive <= D && dp[sp.arrive] > sp.distance + cur.y) {
						dp[sp.arrive] = sp.distance + cur.y;
						q.add(new Point(sp.arrive, sp.distance + cur.y));
					} 
				}
			}
		}
		System.out.println(dp[D]);
		br.close();
	}
}