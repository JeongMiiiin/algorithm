import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr) {
        int len = arr.length;
        int min = Integer.MAX_VALUE;
        int idx = 0;
        for(int i=0; i < len; i++){
            if(min > arr[i]){
                min = arr[i];
                idx = i;
            }
        }
        int ansIdx = 0;
        for(int i=0; i < len; i++) if(arr[i] != min) arr[ansIdx++] = arr[i];
        if(arr.length == 1) arr[0] = -1;
        else arr = Arrays.copyOf(arr, arr.length - 1);
        return arr;
    }
}