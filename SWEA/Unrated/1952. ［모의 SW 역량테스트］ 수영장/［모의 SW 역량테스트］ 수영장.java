import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// SWEA 1952 - 수영장
public class Solution {
	static int[] cost, plan;
	static int ans;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			cost = new int[4];
			plan = new int[12];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i < cost.length; i++)
				cost[i] = Integer.parseInt(st.nextToken());
			
			ans = cost[cost.length - 1];
 			
			st = new StringTokenizer(br.readLine());
				
			for(int i=0; i < plan.length; i++)
				plan[i] = Integer.parseInt(st.nextToken());
			
			subset(0, 0);
			
			System.out.println("#" + t + " " + ans);
			
		}
	}

	private static void subset(int month, int sum) {
		if(ans <= sum) return;
		
		if(month > 11) {
			ans = Math.min(ans, sum);
			return;
		}
		
		if(plan[month] == 0) {
			subset(month + 1, sum);
		} else {
			subset(month + 1, sum + cost[0] * plan[month]);
			
			subset(month + 1, sum + cost[1]);
			
			subset(month + 3, sum + cost[2]);
		}
	}
}
