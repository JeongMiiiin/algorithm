import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int solution(int[] A, int[] B) {
        //세팅
        PriorityQueue<Integer> a = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> b = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i < A.length; i++){
            a.add(A[i]);
            b.add(B[i]);
        }
        int answer = 0;
        while(!a.isEmpty()){
            if(a.poll() < b.peek()){
                answer++;
                b.poll();
            }
        }
        
        
        return answer;
    }
}