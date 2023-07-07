import java.util.Arrays;
import java.util.Collections;

class Solution {
    public long solution(long n) {
        char[] temp = Long.toString(n).toCharArray();
        Integer[] tempList = new Integer[temp.length];
        for(int i=0; i < temp.length; i++) tempList[i] = temp[i] - '0';
        
        Arrays.sort(tempList, (i1, i2) -> i2 - i1);
        StringBuilder answer = new StringBuilder();
        for(int num : tempList) answer.append(num);
        return Long.parseLong(answer.toString());
    }
}