import java.util.HashMap;


class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int len = enroll.length;
        int[] answer = new int[len];
        
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i < len; i++) map.put(enroll[i], i);
        
        for(int i=0; i < seller.length; i++){
            int totalAmount = amount[i] * 100;
            int curIdx = map.get(seller[i]);
            String next = referral[curIdx];
            while( !next.equals("-") && totalAmount >= 1){
                answer[curIdx] += (totalAmount - (totalAmount / 10)); //90% 더해주기
                totalAmount /= 10; //나머지만 남기기
                curIdx = map.get(next);
                next = referral[curIdx];
            }
            if(totalAmount < 1) answer[curIdx] += totalAmount;
            else answer[curIdx] += (totalAmount - (totalAmount / 10));
        }
        
        return answer;
    }
}