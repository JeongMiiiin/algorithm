import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int size = sequence.length + 1;
        int sum = 0;
        int start = 0;
        int end = 0;
        int resultStart = 0;
        int resultEnd = 0;
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i < sequence.length; i++){
            int target = sequence[i];
            sum += target;
            q.add(target);
            if(sum == k){
                if(size > q.size()){
                    size = q.size();
                    resultStart = start;
                    resultEnd = end;
                }
            } else if(sum > k){
                while(sum > k){
                    int prev = q.poll();
                    sum -= prev;
                    start++;    
                }
                if(sum == k){
                    if(size > q.size()){
                        size = q.size();
                        resultStart = start;
                        resultEnd = end;
                    }   
                }
            }
            end++;
        }
        
        int[] answer = {resultStart, resultEnd};
        return answer;
    }
}