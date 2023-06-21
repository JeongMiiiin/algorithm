import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        for(int i=1; i <= order.length; i++){
            if(i == order[answer]) answer++;
            else stack.push(i);
            
            while(!stack.isEmpty() && stack.peek() == order[answer]){
                stack.pop();
                answer++;
            }
        }
        
        return answer;
    }
}