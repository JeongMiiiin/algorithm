import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end - begin) + 1];
        for(int i= (int) begin, idx = 0; i <= (int) end; i++, idx++) answer[idx] = getMaxDivisorExceptMe(i);
        return answer;
    }
    
    private static int getMaxDivisorExceptMe(int n) {
        if (n == 1) return 0;

        List<Integer> temp = new ArrayList<>();
        
        
        
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                temp.add(i);
                if (n / i <= 10000000) return n / i;
            }

        }
        if (!temp.isEmpty()) return temp.get(temp.size() - 1);

        return 1;
    }
}