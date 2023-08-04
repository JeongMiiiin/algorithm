import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(String[] babbling) {
        List<String> possible = new ArrayList<>();
        possible.add("aya");
        possible.add("ye");
        possible.add("woo");
        possible.add("ma");
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        for(String s : babbling){
            //초기화
            sb.setLength(0);
            char[] cList = s.toCharArray();
            boolean flag = true;
            int prevIdx = -1;
            for(char c : cList){
                sb.append(c);
                if(sb.length() > 3){
                    flag = false;
                    break;
                } else{
                    int curIdx = possible.indexOf(sb.toString());
                    if(curIdx == -1) continue;
                    else if(curIdx == prevIdx){
                        flag = false;
                        break;
                    } else {
                        prevIdx = curIdx;
                        sb.setLength(0);
                    }
                }
            }
            
            if(flag && sb.length() == 0) answer++;
        }
        
        
        return answer;
    }
}