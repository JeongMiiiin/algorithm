class Solution{
    public int solution(int n, int a, int b){
        int answer = 1;
        
        while(true){
            if(a - 1 == b && b / 2 + 1 == a / 2) break;
            if(b - 1 == a && a / 2 + 1 == b / 2) break;
            answer++;
            if(a % 2 == 1) a = a / 2 + 1;
            else a /= 2;
            if(b % 2 == 1) b = b / 2 + 1;
            else b /= 2;
        }

        return answer;
    }
}