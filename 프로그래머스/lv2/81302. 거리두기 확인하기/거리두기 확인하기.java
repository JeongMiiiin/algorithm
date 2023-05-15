import java.util.*;
/*
프로그래머스 - 거리두기 확인하기
1. 대기실은 5개이며, 각 대기실은 5 x 5 크기
2. 거리두기를 위하여 응시자들 끼리는 맨해튼 거리가 2 이하로 앉으면 안됨.
맨해튼 거리 - (Math.abs(r1 - r2) + Math.abs(c1 - c2))
3. 단, 응시자가 앉아있는 자리 사이가 파티션으로 막혀있을 경우에는 거리가 2 이하여도 허용

5개의 대기실을 본 죠르디는 각 대기실에서 응시자들이 거리두기를 잘 지키고 있는지 알고 싶어졌음.
자리에 앉아있는 응시자들의 정보와 대기실 구조를 대기실별로 담은 2차원 문자열 배열 places가 주어짐.
P : 응시자가 앉아있는 자리
O : 빈 테이블
X : 파이션

*/
class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        int answerIdx = 0;
        
        //상 우 하 좌
        int[] dr1 = {-1, 0, 1, 0};
        int[] dc1 = {0, 1, 0, -1};
        
        //각 대기실 확인
        for(String[] sList : places){
            
            char[][] map = new char[5][5];
            //맵 세팅
            for(int i=0; i < 5; i++){
                char[] cList = sList[i].toCharArray();
                for(int j=0; j < 5; j++) map[i][j] = cList[j];
            }
            
            //대기실에 응시자들이 거리를 지키고 있는지 확인
            boolean flag = true;
            outer : for(int i=0; i < 5; i++){
                for(int j=0; j < 5; j++){
                    //응시자가 있는 경우
                    if(map[i][j] == 'P'){
                        int nr, nc;
                        for(int d = 0; d < 4; d++){
                            nr = i + dr1[d];
                            nc = j + dc1[d];
                            //맵에서 벗어나거나 X인 경우 패스
                            if((nr < 0 || nr >= 5 || nc < 0 || nc >= 5) || map[nr][nc] == 'X') continue;
                            //응시자가 있는 경우 거리두기를 지키고 있지 않으므로 종료
                            if(map[nr][nc] == 'P'){
                                flag = false;
                                break outer;
                            } else { //빈 테이블인 경우 대각선은 한번 더 직선은 두번 더 가보기
                                int diagonal = nc;
                                //상하에 위치한 경우
                                if(d % 2 == 0){
                                    for(int z= -1; z < 2; z += 2){
                                        diagonal = nc + z;
                                        if( !(nr < 0 || nr >= 5 || diagonal < 0 || diagonal >= 5) && map[nr][diagonal] == 'P'){
                                            flag = false;
                                            break outer;
                                        }
                                    }
                                } else {
                                    diagonal = nr;
                                    for(int z= -1; z < 2; z += 2){
                                        diagonal = nr + z;
                                        if( !(diagonal < 0 || diagonal >= 5 || nc < 0 || nc >= 5) && map[diagonal][nc] == 'P'){
                                            flag = false;
                                            break outer;   
                                        }
                                    }
                                }
                                nr += dr1[d];
                                nc += dc1[d];
                                //맵에서 벗어나거나 X인 경우 패스
                                if((nr < 0 || nr >= 5 || nc < 0 || nc >= 5) || map[nr][nc] == 'X') continue;
                                //응시자가 있는 경우 거리두기를 지키고 있지 않으므로 종료
                                if(map[nr][nc] == 'P'){
                                    flag = false;
                                    break outer;
                                }
                                
                                
                                
                            }
                        }
                        
                        
                    }
                }
            }
            
            
            //거리를 지키고 있는지 확인해서 세팅
            answer[answerIdx++] = flag ? 1 : 0;
        }
        
        
        return answer;
    }
}