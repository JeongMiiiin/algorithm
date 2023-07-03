import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (x,y) -> x[0] - y[0] != 0 ? x[0] - y[0] : x[1] - y[1]);
        int answer = 1;
        //초기값 세팅
        int[] cur = new int[2];
        cur[0] = routes[0][0];
        cur[1] = routes[0][1];
        for(int i=1; i < routes.length; i++){
            if(cur[1] >= routes[i][0]){
                cur[0] = Math.max(cur[0], routes[i][0]);
                cur[1] = Math.min(cur[1], routes[i][1]);
            } else {
                answer++;
                cur[0] = routes[i][0];
                cur[1] = routes[i][1];
            }
        }
        return answer;
    }
}