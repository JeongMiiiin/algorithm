import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        
        HashMap<String, Integer> hm = new HashMap<>();
        for(int i=0; i < 10; i++) hm.put(discount[i], hm.getOrDefault(discount[i], 0) + 1);
        
        int answer = 0;
        if(check(want, number, hm)) answer++;
        
        for(int i=10; i < discount.length; i++){
            hm.put(discount[i], hm.getOrDefault(discount[i], 0) + 1);
            if(hm.get(discount[i - 10]) == 1) hm.remove(discount[i - 10]);
            else hm.put(discount[i - 10], hm.get(discount[i - 10]) - 1);
            
            if(check(want, number, hm)) answer++;
        }
        
        return answer;
    }
    
    //원소가 알맞게 있는지 확인해주는 함수
    public static boolean check(String[] want, int[] number, HashMap<String, Integer> hm){
        //원소가 맞지 않게 있으면 false
        for(int i=0; i < want.length; i++) if(hm.get(want[i]) == null || hm.get(want[i]) != number[i]) return false;
        
        return true;
    }
}