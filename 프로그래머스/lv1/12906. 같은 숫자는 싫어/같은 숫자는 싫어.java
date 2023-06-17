import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);
        for(int i=1; i < arr.length; i++){
            if(stack.peek() != arr[i]) stack.push(arr[i]);
        }
        int size = stack.size();
        
        int[] answer = new int[size];
        for(int i = size - 1; i >= 0; i--) answer[i] = stack.pop();
        
        return answer;
    }
}