import java.util.Arrays;
class Solution {
    
    public int[] solution(String[] keymap, String[] targets) {
        int[] alphabet = new int[26];
        Arrays.fill(alphabet, Integer.MAX_VALUE);
        for(int i=65; i <= 90; i++){
            String s = String.valueOf((char) i);
            for(int j=0; j < keymap.length; j++){
                int idx = keymap[j].indexOf(s) + 1;
                if(idx > 0) alphabet[i - 65] = Math.min(alphabet[i - 65], idx);
            }
        }
        
        int[] answer = new int[targets.length];
        for(int i=0; i < targets.length; i++){
            int cnt = 0;
            char[] cList = targets[i].toCharArray();
            for(char c : cList){
                int idx = ((int) c) - 65;
                if(alphabet[idx] == Integer.MAX_VALUE){
                    cnt = -1;
                    break;
                } else cnt += alphabet[idx];
            }
            answer[i] = cnt;
        }
        
        return answer;
    }
}