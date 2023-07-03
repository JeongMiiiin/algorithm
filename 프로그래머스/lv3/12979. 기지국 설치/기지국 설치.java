class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int range = w * 2 + 1;
        
        //처음 기지국을 만나기 전에 커버해야할 범위
        int cover = stations[0] - w - 1;
        //커버해야할 범위가 있는 경우
        if(cover > 0){
            int cnt = cover / range;
            if(cover % range > 0) cnt++;
            answer += cnt;
        }
        
        for(int i=0; i < stations.length - 1; i++){
            cover = (stations[i + 1] - w - 1) - (stations[i] + w);
            if(cover > 0){
                int cnt = cover / range;
                if(cover % range > 0) cnt++;
                answer += cnt;
            }
        }
        
        cover = n - (stations[stations.length - 1] + w);
        if(cover > 0){
            int cnt = cover / range;
            if(cover % range > 0) cnt++;
            answer += cnt;
        }
        
        
        return answer;
    }
}