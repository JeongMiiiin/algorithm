class Solution {
    public int solution(int[][] sizes) {
        int wid = Integer.MIN_VALUE;
        int hei = Integer.MIN_VALUE;
        
        for(int[] target : sizes){
            int targetWid = target[0];
            int targetHei = target[1];
            if(targetWid <= targetHei){
                wid = Math.max(targetHei, wid);
                hei = Math.max(targetWid, hei);
            } else {
                wid = Math.max(targetWid, wid);
                hei = Math.max(targetHei, hei);
            }
            
        }
        
        int answer = wid * hei;
        return answer;
    }
}