class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for(int num=1; num <= number; num++){
            int divi = divisor(num);
            if(divi > limit) answer += power;
            else answer += divi;
        }
        
        return answer;
    }
    
    private static int divisor(int num){
        if(num == 1) return 1;
        int result = 0;
        for(int n=1; n < Math.sqrt(num); n++) if(num % n == 0) result += 2;
        
        if(num % Math.sqrt(num) == 0) result++;
        
        return result;
    }
}