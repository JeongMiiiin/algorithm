import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        
        for(String s : operations){
            String[] cur = s.split(" ");
            String cmd = cur[0];
            int num = Integer.parseInt(cur[1]);
            if(cmd.equals("D")){ 
                if(tm.isEmpty()) continue;
                if(num == 1){
                    int maxKey = tm.lastKey();
					if(tm.get(maxKey) == 1) tm.remove(maxKey);
					else tm.put(maxKey, tm.get(maxKey) - 1);
				} else {
					int minKey = tm.firstKey();
					if(tm.get(minKey) == 1) tm.remove(minKey);
					else tm.put(minKey, tm.get(minKey) - 1);
                }
            } else tm.put(num, tm.getOrDefault(num, 0) + 1); //추가하기
        }
        
        int[] answer = new int[2];
        
        //최댓값 최소값이 남아있을 때
        if(!tm.isEmpty()){
            answer[0] = tm.lastKey();   
            answer[1] = tm.firstKey();
        }
        return answer;
    }
}