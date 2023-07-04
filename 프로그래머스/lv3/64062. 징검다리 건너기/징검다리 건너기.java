import java.util.ArrayDeque;

class Solution {
    public int solution(int[] stones, int k) {
      int answer = Integer.MAX_VALUE;
        
      ArrayDeque<int[]> dq = new ArrayDeque<>();

      for(int i = 0; i < stones.length; i++) {
         int[] temp = {i, stones[i]};
         int cur = stones[i];
         if(!dq.isEmpty() && dq.peek()[0] <= temp[0] - k) dq.pollFirst();
         while(!dq.isEmpty() && dq.peekLast()[1] <= temp[1]) dq.pollLast();
         dq.add(temp);
         if(temp[0] >= k - 1) answer = Math.min(answer, dq.peekFirst()[1]);
      }

      return answer;
    }
}