/*

*/

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        long maxDir = (long) d * d;
        int max = d;
        for(int i=0; i <= d; i += k){
            long dir = ((long) i * i) + ((long) max * max);
            
            //최대가 거리를 벗어난 경우
            while(dir > maxDir){
                max--;
                dir = ((long) i * i) + ((long) max * max);
            }
            
            answer += (max / k) + 1;
        }
        
        return answer;
    }
}