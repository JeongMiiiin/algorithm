import java.util.Set;
import java.util.HashSet;

class Solution {
    public int[] solution(int n, String[] words) {
        int orderNum = 0;
        int turnNum = 0;
        char lastC = words[0].charAt(words[0].length() - 1);
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        for(int i=1; i < words.length; i++){
            //중복된 글자거나 끝말잇기가 안되는 경우
            if(set.contains(words[i]) || lastC != words[i].charAt(0)){
                orderNum = i % n + 1;
                turnNum = i / n + 1;
                break;
            }
            lastC = words[i].charAt(words[i].length() - 1);
            set.add(words[i]);
        }
        
        
        int[] answer = {orderNum, turnNum};

        return answer;
    }
}