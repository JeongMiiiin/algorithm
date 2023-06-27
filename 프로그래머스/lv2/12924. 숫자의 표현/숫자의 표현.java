class Solution {
    public int solution(int n) {
        int answer = 0;
        for(int i=1; i <= n; i++){
            int temp = i;
            for(int j=i + 1; j <= n; j++){
                temp += j;
                //n을 넘어설 때 연속 종료
                if(temp >= n) break;
            }
            //n이 완성되었을 때
            if(temp == n) answer++;
        }
        
        return answer;
    }
}