class Solution {
    public int solution(String dirs) {
        boolean[][] visited = new boolean[21][21];
        int answer = 0;
        
        //상 우 하 좌
        int[] dr1 = {-1, 0, 1, 0};
        int[] dc1 = {0, 1, 0, -1};
        
        int curR = 10, curC = 10;
        
        for(char c : dirs.toCharArray()){
            int d = 0;
            switch(c){
                case 'R' :
                    d = 1;
                    break;
                case 'D' :
                    d = 2;
                    break;
                case 'L' :
                    d = 3;
                    break;
                default :
                    break;
            }
            curR += dr1[d];
            curC += dc1[d];
            
            //맵에서 벗어나는 명령어인 경우 원복하고 패스
            if(curR < 0 || curR > 20 || curC < 0 || curC > 20){
                curR -= dr1[d];
                curC -= dc1[d];
                continue;
            }
            
            //방문하지 않은 길인 경우
            if( !visited[curR][curC] ){
                visited[curR][curC] = true;
                answer++;
            }
            curR += dr1[d];
            curC += dc1[d];
        }
        
        return answer;
    }
}