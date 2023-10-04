class Solution {
    public int solution(String s) {
        int answer = 0;
        int temp1 = 0, temp2 = 0;
        char compare = 'A';
        for(char c : s.toCharArray()){
            if(temp1 == 0){
                 compare = c;
                 temp1++;
                continue;
            }
            if(compare == c) temp1++;
            else {
                if(++temp2 == temp1){
                    temp1 = 0;
                    temp2 = 0;
                    answer++;
                }
            }
        }
        if(temp1 > 0) answer++;
        return answer;
    }
}