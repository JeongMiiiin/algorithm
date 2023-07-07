class Solution {
    boolean solution(String s) {
        char[] cList = s.toCharArray();
        int pCnt = 0, yCnt = 0;
        for(char c : cList){
            if(c == 'p' || c == 'P') pCnt++;
            if(c == 'y' || c == 'Y') yCnt++;
        }
        
        return pCnt == yCnt;
    }
}