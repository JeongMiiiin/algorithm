/*
성격 유형 검사
지표번호
1. R, T
2. C, F
3. J, M
4. A, N
검사지에는 총 n개의 질문이 있고 아래와 같은 7개의 선택지가 존재
매우비동의 -> 앞 유형에 3점, 비동의 -> 앞 유형에 2점, 약간 비동의 -> 앞 유형에 1점
모르겠음 -> 둘 다 0점
매우 동의 -> 뒤 유형에 3점, 동의 -> 뒤 유형에 2점, 약간 동의 -> 뒤 유형에 1점
단, 점수가 같은 경우 사전순으로 앞에 위치하는 유형을 보여줘야 함
*/
import java.util.Map;
import java.util.HashMap;

class Solution {
    
    public String solution(String[] survey, int[] choices) {
        //초기 세팅
        Map<Character, Integer> map = new HashMap<>();
        map.put('R', 0);
        map.put('T', 0);
        map.put('C', 0);
        map.put('F', 0);
        map.put('J', 0);
        map.put('M', 0);
        map.put('A', 0);
        map.put('N', 0);
        
        for(int i=0; i < survey.length; i++){
            char[] cList = survey[i].toCharArray();
            if(choices[i] < 4) map.put(cList[0], map.get(cList[0]) + (4 - choices[i]));
            else if(choices[i] > 4) map.put(cList[1], map.get(cList[1]) + (choices[i] - 4));
        }
        
        StringBuilder sb = new StringBuilder();
        //유형들 비교
        sb.append(compare(map, 'R', 'T'));
        sb.append(compare(map, 'C', 'F'));
        sb.append(compare(map, 'J', 'M'));
        sb.append(compare(map, 'A', 'N'));
        
        return sb.toString();
    }
    
    private static Character compare(Map<Character, Integer> map, Character c1, Character c2){
        return map.get(c1) >= map.get(c2) ? c1 : c2;
    }
}