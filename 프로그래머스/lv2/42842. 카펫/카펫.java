import java.util.*;

/*
가로길이가 세로 길이보다 같거나 길다.
*/

class Solution {
    public int[] solution(int brown, int yellow) {
        int wid = 0, hei = 0;
        
        boolean flag = false;
        int i = 1;
        while(!flag){
            if(yellow % i == 0){
                int tempWid, tempHei;
                int temp = yellow / i;
                if(temp < i){
                    tempWid = i;
                    tempHei = temp;
                } else {
                    tempWid = temp;
                    tempHei = i;
                }
                
                if(tempWid * 2 + tempHei * 2 + 4 == brown){
                    wid = tempWid + 2;
                    hei = tempHei + 2;
                    flag = true;
                }
                
            }
            i++;
        }
        
        
        int[] answer = {wid, hei};
        return answer;
    }
}