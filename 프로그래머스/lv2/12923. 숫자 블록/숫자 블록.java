/*
소수인 경우에는 1
소수가 아닌 경우 약수 중 자신을 제외한 최댓값 (단, 10000000 이하여야 함)
*/

class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end - begin) + 1];
        for(int i= (int) begin, idx = 0; i <= (int) end; i++, idx++) answer[idx] = find(i);
        return answer;
    }
    
    private static int find(int n) {
        if (n == 1) return 0;

        int result = 0;
        
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                result = Math.max(result, i);
                if (n / i <= 10000000) return n / i;
            }

        }
        if(result > 0) return result;
        return 1;
    }
}