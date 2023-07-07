import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        int len = arr.length;
        int[] answer = new int[len];
        int idx = 0;
        for(int i=0; i < len; i++){
            if(arr[i] % divisor == 0) answer[idx++] = arr[i];
        }
        if(idx == 0){
            answer[0] = -1;
            idx++;
        }
        answer = Arrays.copyOf(answer, idx);
        Arrays.sort(answer);
        
        return answer;
    }
}