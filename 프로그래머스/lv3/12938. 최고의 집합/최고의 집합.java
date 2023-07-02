import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        //개수의 합으로 만들어낼 수 없을 때
        if(s / n == 0) return answer = new int[]{-1};
        else {
            //나눈 값으로 채우기
            Arrays.fill(answer, s / n);
            //나머지 나눠주기
            int remain = s % n;
            int idx = n - 1;
            while(remain-- > 0) answer[idx--]++;
        }
        
        return answer;
    }
}