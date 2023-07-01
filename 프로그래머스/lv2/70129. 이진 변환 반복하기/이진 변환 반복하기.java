class Solution {
    public int[] solution(String s) {
        //1이 될때까지 반복
        StringBuilder sb = new StringBuilder();
        int zeroCnt = 0;
        int changeCnt = 0;
        while(s.length() > 1){
            //StringBuilder 초기화
            sb.setLength(0);
            char[] cList = s.toCharArray();
            //1만 담아주기
            for(char c : cList){
                if(c == '1') sb.append(c);
                else zeroCnt++;
            }
            int size = sb.length();
            s = Integer.toString(size, 2);
            changeCnt++;
        }
        int[] answer = {changeCnt, zeroCnt};
        return answer;
    }
}