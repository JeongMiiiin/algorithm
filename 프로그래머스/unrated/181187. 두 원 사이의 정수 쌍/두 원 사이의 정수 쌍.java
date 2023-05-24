/*
두 원 사이의 정수들을 찾아라

원 한쪽에 포함되는 수들

가로 세로는 (r2 - r1) + 1개 씩
대각선 위치들은 (r2 - r1) * 3
*/

class Solution {
    public long solution(int r1, int r2) {
        
        long answer = 0;

        for (int i = 1; i <= r2; i++) {
            int start = (int) Math.ceil(Math.sqrt((long) r1 * r1 - (long) i * i));
            int end = (int) Math.floor(Math.sqrt((long) r2 * r2 - (long) i * i));

            answer += end - start + 1;
        }

        return answer * 4;
    }
}