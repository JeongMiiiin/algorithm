import java.util.*;

/*
두 개의 단어 begin, target과 단어의 집합 words가 있음.
아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려 함.
1. 한 번에 한 개의 알파벳만 변경 가능 - 1개만 바꿀 수 있는 단어가 없을 때 끝
2. words에 있는 단어로만 변경 가능 - 없을 경우 불가



변환할 수 없는 경우 0을 return
*/

class Solution {
    static boolean flag;
    static int N, ans;
    static String tar, beg;
    static String[] inputs;
    public int solution(String begin, String target, String[] words) {
        //target이 words에 존재하지 않을 때
        if(!Arrays.asList(words).contains(target)) return 0;
        
        //초기화
        beg = begin;
        tar = target;
        inputs = words;
        flag = false;
        N = inputs.length;
        boolean[] visited = new boolean[N];
        ans = Integer.MAX_VALUE;
        
        dfs(0, visited, -1);
        
        //변경에 실패한 경우
        if(!flag) ans = 0;
        
        return ans;
    }
    
    private static void dfs(int depth, boolean[] visited, int selectedIdx){
        //모든 단어를 방문한 경우나 현재 ans보다 크거나 같을 경우 종료
        if(depth > N || depth >= ans) return;
        
        String cur = beg;
        if(selectedIdx > -1) cur = inputs[selectedIdx];
        
        //비교해서 target과 같을 때
        if(selectedIdx > -1 && tar.equals(cur)){
            ans = depth;
            flag = true;
            return;
        }
        
        for(int i=0; i < N; i++){
            if(visited[i]) continue; //선택되었던 경우 패스
            
            //바꿀 수 있는 글자인 경우
            if(compareWord(cur, inputs[i])){
                visited[i] = true;
                boolean[] copyVisited = new boolean[N];
                copyArray(copyVisited, visited);
                dfs(depth + 1, copyVisited, i);

                visited[i] = false;   
            }
        }
        
    }
    
    private static boolean compareWord(String begin, String target){
        char[] beginList = begin.toCharArray();
        char[] targetList = target.toCharArray();
        int cnt = 0;
        for(int i=0; i < beginList.length; i++) if(beginList[i] == targetList[i]) cnt++;
        
        return cnt == begin.length() - 1;
    }
    
    private static void copyArray(boolean[] copyArray, boolean[] originArray){
        for(int i=0; i < originArray.length; i++) copyArray[i] = originArray[i];
    }
}