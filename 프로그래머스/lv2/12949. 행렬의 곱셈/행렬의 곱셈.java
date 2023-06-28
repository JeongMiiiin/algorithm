/*
3x2 2x2 -> 3x2
3x3 3x3 -> 3x3
*/

class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int m = arr1.length;
        int k = arr1[0].length;
        int n = arr2[0].length;
        
        int[][] answer = new int[m][n];
        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                for(int z=0; z < k; z++) answer[i][j] += arr1[i][z] * arr2[z][j];
            }
        }
        return answer;
    }
}