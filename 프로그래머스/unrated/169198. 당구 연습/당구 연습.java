class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        int[] spot1= new int[]{-startX,startY};
        int[] spot2= new int[]{startX,-startY};
        int[] spot3= new int[]{m+(m-startX),startY};
        int[] spot4= new int[]{startX,n+(n-startY)};

        int idx=0;
        for(int[] ball: balls){
            int score =Integer.MAX_VALUE;


            if(startY!=ball[1]||startX<ball[0]){
                score=Math.min(score,(int)Math.pow(Math.abs(spot1[0]-ball[0]),2)
                          +(int)Math.pow(Math.abs(spot1[1]-ball[1]),2));
            }

            if(startX!=ball[0]||startY<ball[1]){
                score=Math.min(score,(int)Math.pow(Math.abs(spot2[0]-ball[0]),2)
                          +(int)Math.pow(Math.abs(spot2[1]-ball[1]),2));
            }

           if(startY!=ball[1]||startX>ball[0]){
                score=Math.min(score,(int)Math.pow(Math.abs(spot3[0]-ball[0]),2)
                          +(int)Math.pow(Math.abs(spot3[1]-ball[1]),2));
           }

           if(startX!=ball[0]||startY>ball[1]){
                score=Math.min(score,(int)Math.pow(Math.abs(spot4[0]-ball[0]),2)
                          +(int)Math.pow(Math.abs(spot4[1]-ball[1]),2));
           }          
            answer[idx++]=score;
        }

        return answer;
    }
}