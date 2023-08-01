class Solution {
    public int solution(int n, int m, int[] section) {
        boolean[] paint = new boolean[n + 1];
        
        //벗겨진 구역 표시
        for(int num : section) paint[num] = true;
        
        int answer = 0;
        for(int num : section){
            if(!paint[num]) continue; //이미 칠해진 경우 패스
            for(int i=num; i <= n && i < num + m; i++) paint[i] = false;
            answer++;
        }
        
        return answer;
    }
}