import java.util.Arrays;

class Solution {
    public String solution(String s) {
        String[] sList = s.split(" ");
        int len = sList.length;
        int[] nList = new int[len];
        for(int i=0; i < len; i++) nList[i] = Integer.parseInt(sList[i]);
        Arrays.sort(nList);
        
        String answer = nList[0] + " " + nList[len - 1];
        return answer;
    }
}