/*
틱택토
규칙
X는 O의 개수와 동일하거나
한개 적어야 한다.
동일해야 할 때
1. 후공까지 낸 경우 (선공이 이기면 X)
*/

class Solution {
    public int solution(String[] board) {
        int Ocnt = 0, Xcnt = 0;
        char[][] map = new char[3][3];
        for(int i=0; i < 3; i++){
            char[] cList = board[i].toCharArray();
            for(int j=0; j < 3; j++) {
                map[i][j] = cList[j];
                if(map[i][j] == 'O') Ocnt++;
                else if(map[i][j] == 'X') Xcnt++;
            }
        }
        
        //차이가 동일하거나 한 개가 아닌 경우
        if(Ocnt - Xcnt != 1 && Ocnt - Xcnt != 0) return 0;
        
        boolean Oflag = false, Xflag = false; 
        //가로 확인
        for(int i=0; i < 3; i++){
            if(map[i][0] == '.') continue; //시작점이 빈칸인 경우 패스
            if(map[i][0] == map[i][1] && map[i][0] == map[i][2]){
                if(map[i][0] == 'O') Oflag = true;
                else Xflag = true;
            }
        }
        
        //세로 확인
        for(int i=0; i < 3; i++){
            if(map[0][i] == '.') continue; //시작점이 빈칸인 경우 패스
            if(map[0][i] == map[1][i] && map[0][i] == map[2][i]){
                if(map[0][i] == 'O') Oflag = true;
                else Xflag = true;
            }
        }
        
        //대각선 확인
        if(map[0][0] != '.'){
            if(map[0][0] == map[1][1] && map[0][0] == map[2][2]){
                if(map[0][0] == 'O') Oflag = true;
                else Xflag = true;
            }
        }
            
        if(map[0][2] != '.'){
            if(map[0][2] == map[1][1] && map[0][2] == map[2][0]){
                if(map[2][0] == 'O') Oflag = true;
                else Xflag = true;
            }
        }
        
        //한개 이상 
        //if(Oflag > 2 || Xflag > 2) return 0;
        
        // 1. 둘 다 이기는 경우
        // 2. 후공이 이겼는데 개수가 동일하지 않을 때
        // 3. 선공이 이겼는데 개수가 동일할 때
        if((Oflag && Xflag) || (Xflag && Ocnt - Xcnt != 0) || (Oflag && Ocnt - Xcnt == 0)) return 0;
        
        return 1;
    }
}