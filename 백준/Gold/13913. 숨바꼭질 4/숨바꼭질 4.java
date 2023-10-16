import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 백준 13913 - 숨바꼭질 4
 * 수빈이는 현재 점 N에 있고, 동생은 점 K에 있음.
 * 수빈이는 걷거나 순간이동 가능
 * 현재 위치가 X일때
 * 걸으면 1초 후에 X-1 또는 X+1로 이동
 * 순간이동하면 1초 후에 X * 2로 이동
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하라
 * 
 * dp로 기억해서 만드는데, pq를 사용해서 짧은 시간부터 확인해서 찾기
 * 
 * */

public class Main {
	static class Position{
		int cur, time;
		String path;
		Position(int cur, int time, String path){
			this.cur = cur;
			this.time = time;
			this.path = path;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		if(K < N) {
			System.out.println(N - K);
			StringBuilder sb = new StringBuilder();
			for(int i=N; i >= K; i--) sb.append(" " + i);
			System.out.println(sb.toString());
			
		} else {
			int[] dp = new int[200001];
			Arrays.fill(dp, Integer.MAX_VALUE);
			Queue<Position> q = new LinkedList<>();
			dp[N] = 0;
			q.add( new Position(N, 0, String.valueOf(N)));
			
			String result = "";
			Position cur;
			while(!q.isEmpty()) {
				cur = q.poll();
				if(cur.cur == K) {
					result = cur.path;
					break;
				}
				
				//-1칸 이동
				if(cur.cur - 1 >= 0 && dp[cur.cur - 1] > cur.time + 1) {
					dp[cur.cur - 1] = cur.time + 1;
					q.add(new Position(cur.cur - 1, cur.time + 1, cur.path + " " + String.valueOf(cur.cur - 1)));
				}
				
				//1칸 이동
				if(cur.cur + 1 <= 100000 && dp[cur.cur + 1] > cur.time + 1) {
					dp[cur.cur + 1] = cur.time + 1;
					q.add(new Position(cur.cur + 1, cur.time + 1, cur.path + " " + String.valueOf(cur.cur + 1)));
				}
				
				//순간 이동
				if(cur.cur * 2 <= 200000 && dp[cur.cur * 2] > cur.time + 1) {
					dp[cur.cur * 2] = cur.time + 1;
					q.add(new Position(cur.cur * 2, cur.time + 1, cur.path + " " + String.valueOf(cur.cur * 2)));
				}
				
			}
			
			System.out.println(dp[K]);
			System.out.println(result);
		}
		
		sc.close();
	}
}