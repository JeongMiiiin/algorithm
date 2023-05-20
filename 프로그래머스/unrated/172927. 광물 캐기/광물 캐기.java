import java.util.*;
/*
프로그래머스 - 광물 캐기

곡괭이 종류와 캘 광물 종류에 따라 피로도가 달라짐
다이아몬드 곡괭이 -> 다이아 : 1, 철 : 1, 돌 : 1
철 곡괭이 -> 다이아 : 5, 철 : 1, 돌 : 1
돌 곡괭이 -> 다이아 : 25, 철 : 5, 돌 : 1

최소한의 피로도로 광물을 캐려고 함
규칙
1. 사용할 수 있는 곡괭이중 아무거나 하나 선택
2. 한 번 사용하기 시작한 곡괭이는 사용할 수 없을 때까지 사용
3. 광물은 주어진 순서대로만 가능
4. 광산에 있는 모든 광물을 캐거나, 더 사용할 곡괭이가 없을 때까지 광물을 캠.

picks -> 가지고 있는 곡괭이의 수 배열 (다이아, 철, 돌)
minerals -> 광물들의 순서 배열

곡괭이의 순열을 통해 최소의 피로도 도출
*/

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int num = picks[0] + picks[1] + picks[2];
        int[][] section = new int[minerals.length / 5 + 1][3];
        int i, pick;
        for(i = 0; i < minerals.length && num > 0; i++) {
            switch(minerals[i].charAt(0))
            {
                case 'd':
                    section[i / 5][0] += 1;
                    section[i / 5][1] += 5;
                    section[i / 5][2] += 25;
                    break;
                case 'i':
                    section[i / 5][0] += 1;
                    section[i / 5][1] += 1;
                    section[i / 5][2] += 5;
                    break;
                case 's':
                    section[i / 5][0] += 1;
                    section[i / 5][1] += 1;
                    section[i / 5][2] += 1;
            }
            if(i % 5 == 4) num--;            
        }
        Arrays.sort(section, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[2] < o2[2]) 
                    return 1;
                else 
                    return -1;
            }
        });
        for(i = 0, pick = 0; i < section.length; i++) {
            while(pick < 3 && picks[pick] == 0) pick++;
            if(pick == 3) break;
            picks[pick]--;
            answer += section[i][pick];
        }
        return answer;
    }
}