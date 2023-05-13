import java.util.*;
/*
프로그래머스 - N진수 게임
규칙
1. 숫자를 0부터 시작해서 차례대로 말함
2. 10 이상의 숫자부터는 한 자리씩 끊어서 말함

*/

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder ans = new StringBuilder();
        
        int idx = 0;
        String num = Integer.toString(idx++, n);
        
        //마지막 순서일 때
        if(m == p) p = 0;
        
        //찾기
        for(int i=1; i <= m * t; i++){
            //숫자 비어있으면 새로 배정
            if(num.length() == 0) num = Integer.toString(idx++, n);
            
            if(i % m == p) ans.append(num.substring(0, 1)); 
            
            num = num.substring(1, num.length());
        }
        
        return ans.toString().toUpperCase();
    }
}