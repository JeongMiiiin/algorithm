import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        
        int answer = -1;
        int n = citations.length;
        
        int temp = 0;
        int i = 0;
        while(i < n){
            if( temp <= citations[i]){
                if(n - i >= temp) answer = temp;
                temp++;
            } else {
                i++;
            }
        }
        
        if(answer == -1) answer = n;
        
        return answer;
    }
}