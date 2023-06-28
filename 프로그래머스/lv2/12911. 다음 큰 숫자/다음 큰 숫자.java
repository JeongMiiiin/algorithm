class Solution {
    public int solution(int n) {
        char[] cList = Integer.toString(n, 2).toCharArray();
        int oneCnt = 0;
        for(char c : cList) if(c == '1') oneCnt++;
        
        int answer = n;
        boolean flag = false;
        while(!flag){
            cList = Integer.toString(++answer, 2).toCharArray();
            int tempCnt = 0;
            for(char c : cList) if(c == '1') tempCnt++;
            if(tempCnt == oneCnt) flag = true;
        }
        return answer;
    }
}