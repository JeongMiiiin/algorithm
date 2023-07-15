class Solution {
    public int solution(String dartResult) {
        int[] answerList = new int[3];
        int answerIdx = 0;
        StringBuilder sb = new StringBuilder();
        char[] cList = dartResult.toCharArray();
        for(int i=0; i < cList.length; i++){
            char c = cList[i];
            //스타상이거나 아차상일때는 패스 -> 이전에 계산해주기 때문
            if(c == '*' || c == '#') continue;
            
            //계산해줘야할 때
            if(c == 'S' || c == 'D' || c == 'T'){
                int target = Integer.parseInt(sb.toString());
                if(c == 'D') target = target * target;
                else if(c == 'T') target = target * target * target;
                answerList[answerIdx] = target;
                
                int nextIdx = i + 1;
                if(nextIdx < cList.length){
                    //아차상인 경우
                    if(cList[nextIdx] == '#') answerList[answerIdx] = answerList[answerIdx] * (-1); 
                    else if(cList[nextIdx] == '*') {
                        int j = answerIdx;
                        while(j > -1 && j > answerIdx - 2) answerList[j] = answerList[j--] * 2;
                    }
                }
                sb.setLength(0);
                answerIdx++;
            } else sb.append(c);
               
        }
        
        int answer = 0;
        for(int i=0; i < 3; i++) answer += answerList[i];
        return answer;
    }
}