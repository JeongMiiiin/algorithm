import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N + 1];
		for(int i=1; i <= N; i++) arr[i] = sc.nextInt();
		int[] dp = new int[N + 1];
		Arrays.fill(dp, 1001);
		Queue<Integer> q = new LinkedList<>();
		int jump = 0;
		q.add(1);
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i < size; i++) {
				int cur = q.poll();
				if(dp[cur] > jump) { //최소로 왔을 때
					dp[cur] = jump;
					int start = cur;
					while(start <= N && start <= cur + arr[cur]) q.add(start++);
				}
			}
			jump++;
		}
		
		System.out.println(dp[N] != 1001 ? dp[N] : -1);
		sc.close();
	}
}