import java.util.Arrays;
import java.util.Collections;

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        char[] cList = s.toCharArray();
        Character[] temp = new Character[cList.length];
        for(int i=0; i < cList.length; i++) temp[i] = cList[i];
        Arrays.sort(temp, Collections.reverseOrder());
        for(int i=0; i < cList.length; i++) sb.append(temp[i]);
        
        return sb.toString();
    }
}