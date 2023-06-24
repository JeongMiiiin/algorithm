import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        List<Character> temp = new ArrayList<>();
        for(char c : skill.toCharArray()) temp.add(c);
        int len = temp.size();
        for(String s : skill_trees){
            char[] cList = s.toCharArray();
            boolean flag = true;
            int idx = 0;
            for(char c : cList){
                if(!temp.contains(c)) continue;
                
                if(temp.get(idx) != c){
                    flag = false;
                    break;
                } else idx++;
            }
            if(flag) answer++;
        }
        
        return answer;
    }
}