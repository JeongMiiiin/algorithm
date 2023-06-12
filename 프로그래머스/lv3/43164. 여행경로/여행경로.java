import java.util.*;

/*
여행경로
항상 INC 공항에서 출발
항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때,
방문하는 공항 경로를 배열에 담아 return
방문 가능한 공항 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로로 return
*/


class Solution {
    
    static int N;
    static String[] ans;
    static String[][] inputs;
    public String[] solution(String[][] tickets) {
        N = tickets.length + 1;
        
        inputs = tickets;
        ans = new String[N];
        boolean[] visited = new boolean[N - 1];
        String[] tempAns = new String[N];
        tempAns[0] = "ICN";
        dfs(1, visited, tempAns, "ICN");
        
        return ans;
    }
    
    private static void dfs(int depth, boolean[] visited, String[] tempAns, String cur){
        if(depth == N){
            if(ans[1] != null){
                StringBuilder ansBuilder = new StringBuilder();
                StringBuilder tempBuilder = new StringBuilder();
                for(int i=0; i < tempAns.length; i++){
                    ansBuilder.append(ans[i]);
                    tempBuilder.append(tempAns[i]);
                }
                
                if(tempBuilder.toString().compareTo(ansBuilder.toString()) < 0) copyStringArray(ans, tempAns);
            } else copyStringArray(ans, tempAns);
            
            return;
        }
        
        for(int i=0; i < inputs.length; i++){
            if(visited[i]) continue;
            
            //출발지가 현재 위치와 같을 경우
            if(cur.equals(inputs[i][0])){
                boolean[] tempVisited = new boolean[N - 1];
                copyVisitArray(tempVisited, visited);
                tempVisited[i] = true;
                String[] temp = new String[N];
                copyStringArray(temp, tempAns);
                temp[depth] = inputs[i][1];
                dfs(depth + 1, tempVisited, temp, temp[depth]);
            }
        }
        
    }
    
    private static void copyVisitArray(boolean[] copyVisited, boolean[] originVisited){
        for(int i=0; i < copyVisited.length; i++) copyVisited[i] = originVisited[i];
    }
    
    private static void copyStringArray(String[] copyString, String[] originString){
        for(int i=0; i < copyString.length; i++) copyString[i] = originString[i];
    }
}