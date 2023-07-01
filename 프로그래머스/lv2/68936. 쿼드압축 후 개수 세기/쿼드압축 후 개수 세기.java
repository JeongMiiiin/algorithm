class Solution {
    static int zeroCnt, oneCnt;
    public int[] solution(int[][] arr) {
        int n = arr.length;
        zeroCnt = 0;
        oneCnt = 0;
        
        find(arr, 0, 0, n);
        
        
        int[] answer = {zeroCnt, oneCnt};
        return answer;
    }
    
    private static void find(int[][] arr, int r, int c, int n){
        int target = arr[r][c];
        
        boolean flag = true;
        outer : for(int i=r; i < r + n; i++){
            for(int j=c; j < c + n; j++){
                if(arr[i][j] != target){
                    flag = false;
                    break outer;
                }
            }
        }
        
        //압축이 될 경우
        if(flag){
            if(target == 1) oneCnt++;
            else zeroCnt++;
        } else { //압축이 안될 경우
            find(arr, r, c, n / 2);//1사분면
            find(arr, r, c + n / 2, n / 2);//2사분면
            find(arr, r + n / 2, c, n / 2);//3사분면
            find(arr, r + n / 2, c + n / 2, n / 2);//4사분면
        }
    }
}