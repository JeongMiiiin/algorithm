import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {
    static String[] userList;
    static List<Integer>[] possibles;
    static int len;
    static boolean[] visited;
    static List<List<String>> set = new ArrayList<>();
    public int solution(String[] user_id, String[] banned_id) {
        userList = user_id;
        len = banned_id.length;
        //각각 가능한 경우의 수를 담을 list배열 생성
        possibles = new ArrayList[len];
        for(int i=0; i < len; i++) possibles[i] = new ArrayList<>();
        //가능한 경우 index 넣어주기
        for(int i=0; i < len; i++) for(int j=0; j < user_id.length; j++) if(possibleId(banned_id[i], user_id[j])) possibles[i].add(j);
        
        visited = new boolean[user_id.length];
        
        comb(0);
        
        return set.size();
    }
    
    private static void comb(int cnt){
        if(cnt == len){
            List<String> temp = new ArrayList<>();
            for(int i=0; i < userList.length; i++) if(visited[i]) temp.add(userList[i]);
            if(!set.contains(temp)) set.add(temp);
            return;
        }
        
        List<Integer> targets = possibles[cnt];
        for(int i=0; i < targets.size(); i++){
            int target = targets.get(i);
            if(visited[target]) continue;
            visited[target] = true;
            comb(cnt + 1);
            visited[target] = false;
        }
    }
    
    //가능한 글자인지 판단해주는 함수
    private static boolean possibleId(String ban_id, String user_id){
        //글자 수가 안 맞을 경우 false 리턴 후 종료
        if(ban_id.length() != user_id.length()) return false;
        
        char[] ban = ban_id.toCharArray();
        char[] user = user_id.toCharArray();
        for(int i=0; i < ban.length; i++){
            if(ban[i] == '*') continue; //가린 문자면 패스
            else if(ban[i] != user[i]) return false; //두 문자가 다른 경우 false 리턴 후 종료
        }
        
        return true;
    }
}