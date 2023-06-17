import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = {};
        answer = new int[prices.length];
        for(int i = 0; i < prices.length; ++i)
        {
            for(int j = i+1; j < prices.length; ++j)
            {
                if(prices[i] <= prices[j])
                    answer[i]++;
                else
                {
                    //if(answer[i] == 0)
                        answer[i]++;
                    break;
                }

            }
        }

        return answer;
    }
}