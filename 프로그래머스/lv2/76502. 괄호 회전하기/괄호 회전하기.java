import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;
        char[] c = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        outer : for(int x=0; x < c.length; x++){
            stack.clear();
            for(int i=x; i < c.length; i++){
                switch(c[i]){
                    case '}' :
                        if(stack.isEmpty() || stack.pop() != '{') continue outer;
                        break;
                    case ')' :
                        if(stack.isEmpty() || stack.pop() != '(') continue outer;
                        break;
                    case ']' :
                        if(stack.isEmpty() || stack.pop() != '[') continue outer;
                        break;
                    default :
                        stack.push(c[i]);
                        break;
                }
            }
            
            for(int i=0; i < x; i++){
                switch(c[i]){
                    case '}' :
                        if(stack.isEmpty() || stack.pop() != '{') continue outer;
                        break;
                    case ')' :
                        if(stack.isEmpty() || stack.pop() != '(') continue outer;
                        break;
                    case ']' :
                        if(stack.isEmpty() || stack.pop() != '[') continue outer;
                        break;
                    default :
                        stack.push(c[i]);
                        break;
                }
            }
            
            //스택에 남아있을 때
            if(!stack.isEmpty()) continue outer;    
            
            //모두 통과했으면
            answer++;
        }
        
        return answer;
    }
}