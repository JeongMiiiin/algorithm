import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        boolean flag = true;
        Queue<String> q1 = new LinkedList<>();
        Queue<String> q2 = new LinkedList<>();
        for(String s : cards1) q1.add(s);
        for(String s : cards2) q2.add(s);
        for(String s : goal){
            if(s.equals(q1.peek())) q1.poll();
            else if(s.equals(q2.peek())) q2.poll();
            else {
                flag = false;
                break;
            }
        }
        
        
        if(!flag) answer = "No";
        return answer;
    }
}