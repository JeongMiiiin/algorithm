import java.util.ArrayList;

class Solution {
    static ArrayList<int[]> answer = new ArrayList<>();
    public int[][] solution(int n) {
        hanoi(n, 1, 2, 3);
        
        return answer.toArray(new int[answer.size()][2]);
    }
    
    void hanoi(int n, int start, int mid, int end) {
        if (n == 1) {
            answer.add(new int[]{start, end});
            return;
        }
        
        //n - 1 개의 원판을 start -> mid
        hanoi(n - 1, start, end, mid);
        answer.add(new int[]{start, end});
        //n - 1 개의 원판을 mid -> end
        hanoi(n - 1, mid, start, end);
    }
}