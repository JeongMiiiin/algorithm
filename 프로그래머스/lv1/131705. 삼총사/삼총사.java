import java.util.Arrays;

class Solution {
    static int answer;
    static int[] numbers = new int[3];
    public int solution(int[] number) {
        answer = 0;
        
        comb(number, 0, 0);
        
        return answer;
    }
    
    private static void comb(int[] arr, int cnt, int start){
        if(cnt == 3){
            int total = 0;
            for(int n : numbers) total += n;
            if(total == 0) answer++;
            return;
        }
        
        for(int i=start; i < arr.length; i++){
            numbers[cnt] = arr[i];
            comb(arr, cnt + 1, i + 1);
        }
    }
}