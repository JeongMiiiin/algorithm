import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        
        //1. 순위 계산
        int cnt = 0;
        int zeroCnt = 0;
        for(int lotto: lottos){
        	//2. 알 수 없는 번호인 경우 zeroCnt를 올리고 패스
            if(lotto == 0) {
                zeroCnt++;
                continue;
            }
            //3. 0이 아닌 경우 실제 당첨번호와 비교
            for(int win_num:win_nums){
                if(win_num == lotto){ //현재 번호와 맞으면 cnt를 올리고 패스
                    cnt++;
                    break;
                }
            }
        }
        //4. 맞출 수 있는 번호의 개수와 가능한 최고순위 리턴
        return new int[]{7-Math.max(cnt+zeroCnt,1),7-Math.max(cnt,1)};
    }
}