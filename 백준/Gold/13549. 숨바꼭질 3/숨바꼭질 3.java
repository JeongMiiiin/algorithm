import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 *백준 13549 - 숨바꼭질 3
 *수빈이는 동생과 숨바꼭질을 하고 있음.
 *현재 점 N에 있고, 동생은 점 K에 있음.
 *걷거나 순간이동 가능.
 *위치가 X일 때 걷는다면 1초 후 X-1 혹은 X + 1로 이동
 *순간이동을 하는 경우 0초 후에 2*X로 이동.
 *동생을 찾는 가장 빠른 시간 출력
 * */
public class Main {
	static class Position implements Comparable<Position>{
		int cur, time;
		
		public Position(int cur, int time) {
			this.cur = cur;
			this.time = time;
		}

		@Override
		public int compareTo(Position o) {
			// TODO Auto-generated method stub
			return this.time - o.time;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] dp = new int[200001];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[N] = 0;
		PriorityQueue<Position> pq = new PriorityQueue<>();
		pq.add(new Position(N, 0));
		Position cur;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			if(dp[K] < cur.time) break;
			if(cur.cur - 1 >= 0 && dp[cur.cur - 1] > cur.time + 1) {
				dp[cur.cur - 1] = cur.time + 1;
				pq.add(new Position(cur.cur - 1, dp[cur.cur - 1]));
			}
			if(cur.cur + 1 <= 100000 && dp[cur.cur + 1] > cur.time + 1) {
				dp[cur.cur + 1] = cur.time + 1;
				pq.add(new Position(cur.cur + 1, dp[cur.cur + 1]));
			}
			if(cur.cur * 2 <= 200000 && dp[cur.cur * 2] > cur.time) {
				dp[cur.cur * 2] = cur.time;
				pq.add(new Position(cur.cur * 2, dp[cur.cur * 2]));
			}
		}
		System.out.println(dp[K]);
		sc.close();
	}
}