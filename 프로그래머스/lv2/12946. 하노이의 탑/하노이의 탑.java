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
        
        hanoi(n - 1, start, end, mid);
        answer.add(new int[]{start, end});
        hanoi(n -1, mid, start, end);
    }
}