import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        //초기화
		Queue<Integer> q1 = new LinkedList<>();
		Queue<Integer> q2 = new LinkedList<>();
		
		int len = queue1.length;
		
		long Q1Total = 0, Q2Total = 0;
		for(int i=0; i < len; i++) {
			Q1Total += queue1[i];
			q1.offer(queue1[i]);
			Q2Total += queue2[i];
			q2.offer(queue2[i]);
		}
		
		int ans = 0;
		while(Q1Total != Q2Total) {
			ans++;
			int prev;
			if(Q1Total > Q2Total) {
				prev = q1.poll();
				Q1Total -= prev;
				Q2Total += prev;
				q2.offer(prev);
			} else {
				prev = q2.poll();
				Q2Total -= prev;
				Q1Total += prev;
				q1.offer(prev);
			}
			
			if(ans > len * 4) {
				ans = -1;
				break;
			}
		}
		
		
        return ans;
    }
	
}