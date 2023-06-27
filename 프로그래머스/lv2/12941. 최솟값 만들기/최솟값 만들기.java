//값을 보내보기
import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int solution(int []A, int []B){
        int len = B.length;
        Arrays.sort(A);
        Integer[] b = new Integer[len];
        for(int i=0; i < len; i++) b[i] = B[i];
        Arrays.sort(b, Collections.reverseOrder());
        int answer = 0;
        for(int i=0; i < len; i++) answer += A[i] * b[i];

        return answer;
    }
    
}