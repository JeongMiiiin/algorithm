//A B C D E F G H I J K L M N O P Q R S T U V W X Y Z

class Solution {
    static int answer, k;
    static char[] cList;
    public int solution(String name) {
        answer = Integer.MAX_VALUE;
        cList = name.toCharArray();
        k = cList.length;
        boolean[] visited = new boolean[k];
        visited[0] = true;
        dfs(changeAlphabet(cList[0]), 1, 0, 1, 0, visited);
        dfs(changeAlphabet(cList[0]), -1, 0, 1, 1, visited);
        
        return answer;
    }
    
    public static void dfs(int ans, int cur, int aCnt, int cnt, int dir, boolean[] visited){
        //이미 최소값을 넘어선 경우
        if(ans >= answer) return;
        
        //모든 값을 다 바꾼 경우
        if(cnt == k){
            answer = ans;
            return;
        }
        
        if(cur < 0) cur = k - 1;
        else if(cur >= k) cur = 0;
        
        boolean[] temp1Visited = new boolean[k];
        copyVisited(visited, temp1Visited);
        boolean[] temp2Visited = new boolean[k];
        copyVisited(visited, temp2Visited);
        
        temp1Visited[cur] = true;
        temp2Visited[cur] = true;
        if(visited[cur]){    
            if(dir == 0) dfs(ans, cur + 1, aCnt + 1, cnt, 0, temp1Visited);
            else dfs(ans, cur - 1, aCnt + 1, cnt, 1, temp2Visited);
        } else if(cList[cur] == 'A'){
            dfs(ans, cur + 1, aCnt + 1, cnt + 1, 0, temp1Visited);
            dfs(ans, cur - 1, aCnt + 1, cnt + 1, 1, temp2Visited);
        } else {
            ans += changeAlphabet(cList[cur]);
            ans += aCnt + 1;
            aCnt = 0;
            dfs(ans, cur + 1, aCnt, cnt + 1, 0, temp1Visited);
            dfs(ans, cur - 1, aCnt, cnt + 1, 1, temp2Visited);
        }
    }
    
    private static void copyVisited(boolean[] origin, boolean[] copy){
        for(int i=0; i < origin.length; i++) copy[i] = origin[i];
    }
    
    private static int changeAlphabet(char c){
        if(c - 'A' > 13) return 'Z' - c + 1;
        else return c - 'A';
    }
}