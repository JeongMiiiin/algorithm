import java.util.HashMap;

class Solution {
    public int solution(int[] topping) {
        
        HashMap<Integer, Integer> s1 = new HashMap<>();
        HashMap<Integer, Integer> s2 = new HashMap<>();
        HashMap<Integer, Integer> s3 = new HashMap<>();
        HashMap<Integer, Integer> s4 = new HashMap<>();
        
        int mid = topping.length / 2;
        
        for(int i=0; i < topping.length; i++){
            int num = topping[i];
            
            if(i < mid){
                s1.put(num, s1.getOrDefault(num, 0) + 1);
                s3.put(num, s3.getOrDefault(num, 0) + 1);
            } else {
                s2.put(num, s2.getOrDefault(num, 0) + 1);
                s4.put(num, s4.getOrDefault(num, 0) + 1);
            }
        }
        
        int answer = 0;
        if(s1.size() == s2.size()) answer++;
        
        while(mid < topping.length){
            int num = topping[mid++]; 
            if(s2.get(num) == 1) s2.remove(num);
            else s2.put(num, s2.get(num) - 1);
            
            s1.put(num, s1.getOrDefault(num, 0) + 1);
            
            if(s1.size() == s2.size()) answer++;
        }
        
        mid = topping.length / 2;
        
        while(mid > 0){
            int num = topping[--mid];
            
            if(s3.get(num) == 1) s3.remove(num);
            else s3.put(num, s3.get(num) - 1);
            
            s4.put(num, s4.getOrDefault(num, 0) + 1);
            
            if(s3.size() == s4.size()) answer++;
        }
        
        return answer;
    }
}