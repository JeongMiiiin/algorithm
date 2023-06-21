import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int[] elements) {
        
        HashSet<Integer> hs = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        
        int len = elements.length;
        
        for(int i=0; i < len; i++){
            q.clear();
            int cnt = 0;
            int j = 0;
            while(j < i + 1){
                int num = elements[j++];
                cnt += num;
                q.add(num);
            }
            
            hs.add(cnt);
            
            int cur = j;
            for(int z = j; z < len * 2; z++){
                cnt -= q.poll();
                if(cur >= len) cur = 0;
                int num = elements[cur++];
                cnt += num;
                q.add(num);
                
                hs.add(cnt);
            }
        }
        
        
        return hs.size();
    }
}