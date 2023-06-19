import java.util.*;

class Solution {
    public int solution(int storey) {
        List<Integer> nList = new ArrayList<>();
        while(storey > 0){
            nList.add(storey % 10);
            storey /= 10;
        }
        
        int answer = 0;
        for(int i= nList.size() - 1; i > -1; i--){
            int cur = nList.get(i);
            if(cur > 5){ //덧셈이 더 빠를 경우
                answer += 10 - cur;
                if(i > 0) nList.set(i - 1, nList.get(i - 1) + 1); 
                else answer++;
            } else if(cur == 5){
              if(i > 0 && nList.get(i - 1) >= 5){
                  answer += 10 - cur;
                  nList.set(i - 1, nList.get(i - 1) + 1);
              } else answer += cur;
            } else answer += cur;
        }
        
        return answer;
    }
}