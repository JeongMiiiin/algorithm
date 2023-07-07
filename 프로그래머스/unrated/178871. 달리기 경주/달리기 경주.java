import java.util.Map;
import java.util.HashMap;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        int len = players.length;
        Map<String, Integer> player = new HashMap<>();
        Map<Integer, String> rank = new HashMap<>();
        for(int i=0; i < len; i++) {
            player.put(players[i], i + 1);
            rank.put(i + 1, players[i]);
        }
        for(String calling : callings){
            int cur = player.get(calling); //현재 순위
            
            //바로 앞에 있는 사람
            int prev = cur - 1;
            String prevTarget = rank.get(prev);
            
            player.put(calling, prev);
            player.put(prevTarget, cur);
            rank.put(prev, calling);
            rank.put(cur, prevTarget);
        }
        
        String[] answer = new String[players.length];
        for( Map.Entry<Integer, String> entry : rank.entrySet() ) answer[entry.getKey() - 1] = entry.getValue();
        
        return answer;
    }
}