class Solution {
    public int[] solution(long n) {
        int[] answer = new int[String.valueOf(n).length()];
        for(int i=0; i < answer.length; i++){
            int target = (int) (n % 10);
            answer[i] = target;
            n -= target;
            n /= 10;
        }
        return answer;
    }
}