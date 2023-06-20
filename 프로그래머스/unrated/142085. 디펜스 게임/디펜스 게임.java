import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        int answer = 0;
        while(answer < enemy.length){
            pq.add(enemy[answer]);
            if(n - enemy[answer] >= 0) n -= enemy[answer];
            else if(k > 0){
                k--;
                n += pq.poll();
                n -= enemy[answer];
            } else break;
            
            answer++;
        }
        
        return answer;
    }
}