import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static class Frequency implements Comparable<Frequency>{
		int idx, cnt;
		public Frequency(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Frequency o) {
			// TODO Auto-generated method stub
			return this.cnt - o.cnt;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = sc.nextInt();
		int[] dp = new int[1001];
		Arrays.fill(dp, Integer.MAX_VALUE);
		PriorityQueue<Frequency> pq = new PriorityQueue<>();
		dp[A] = 0;
		pq.add(new Frequency(A, 0));
		for(int i=0; i < N; i++) {
			if(arr[i] == B) {
				System.out.println(1);
				sc.close();
				return;
			} else {
				pq.add(new Frequency(arr[i], 1));
				dp[arr[i]] = 1;
			}
		}
		int result = 0;
		while(!pq.isEmpty()) {
			Frequency cur = pq.poll();
			if(cur.idx == B) {
				result = cur.cnt;
				break;
			}
			if(cur.idx - 1 > 0 && dp[cur.idx - 1] > cur.cnt + 1) {
				dp[cur.idx - 1] = cur.cnt + 1;
				pq.add(new Frequency(cur.idx - 1, cur.cnt + 1));
			}
			if(cur.idx + 1 <= 1000 && dp[cur.idx + 1] > cur.cnt + 1) {
				dp[cur.idx + 1] = cur.cnt + 1;
				pq.add(new Frequency(cur.idx + 1, cur.cnt + 1));
			}
		}
		
		System.out.println(result);
		sc.close();
	}
}