import java.util.*;

class Solution {
    static class Process{
        int idx, orderNum;
        public Process(int idx, int orderNum) {
            this.idx = idx;
            this.orderNum = orderNum;
        }
    }
    
    public int solution(int[] priorities, int location) {
        int answer = 0, len = priorities.length;
        Integer[] orders = new Integer[len];
        
        Queue<Process> q = new LinkedList<>();
        for(int i=0; i < len; i++){
            int order = priorities[i];
            q.add(new Process(i, order));
            orders[i] = order;
        }
        
        Arrays.sort(orders, Collections.reverseOrder());
        
        Process cur;
        while(!q.isEmpty()){
            cur = q.poll();
            if(cur.orderNum < orders[answer]){ //우선순위가 큰게 있는 경우
                q.add(cur);
                continue;
            } else { //최우선순위인 경우
                answer++;
                if(cur.idx == location) break;
            }
        }
        
        return answer;
    }
}