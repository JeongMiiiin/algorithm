class Solution {
    public boolean solution(int x) {
        int n = x;
        int divide = 0;
        while(n > 0){
            divide += n % 10;
            n /= 10;
        }
        return x % divide == 0;
    }
}