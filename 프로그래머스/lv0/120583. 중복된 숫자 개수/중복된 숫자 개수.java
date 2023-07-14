class Solution {
    public int solution(int[] array, int n) {
        int answer = 0;
        for(int num : array) if(n == num) answer++;
        return answer;
    }
}