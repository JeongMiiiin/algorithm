class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left + 1)];
        for(long i=left; i <= right; i++){
            long share = i / n + 1;
            long remain = i % n + 1;
            int idx = (int) (i - left);
            if(remain <= share) answer[idx] = (int) share;
            else answer[idx] = (int) remain;
        }
        
        
        return answer;
    }
}