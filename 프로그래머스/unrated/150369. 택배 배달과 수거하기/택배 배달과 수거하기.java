class Solution {
    //n이 가장 먼 곳을 표시하는 변수로 사용
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long ans = 0;
		
		
		int deliverCnt, collectCnt;
		//가장 먼 곳부터 확인하면서 배달 및 수거 하기
		while(n > 0) {
			//가장 먼 곳에 배달을 해야할 때
			if(deliveries[n - 1] > 0 || pickups[n - 1] > 0) {
				ans += n * 2;
				deliverCnt = cap - deliveries[n - 1];
				collectCnt = cap - pickups[n - 1];
				//가장 먼 곳에 배달을 다하고도 가능 개수가 남을 때
				if(deliverCnt > 0) {
					deliveries[n - 1] = 0;
					for(int i = n - 2; i > -1; i--) {
						if(deliverCnt - deliveries[i] > 0) {
							deliverCnt -= deliveries[i];
							deliveries[i] = 0;
						} else {
							deliveries[i] -= deliverCnt;
							break;
						}
					}
				} else deliveries[n - 1] -= cap;
				
				//가장 먼 곳에 수거를 다하고도 가능 개수가 남을 때
				if(collectCnt > 0) {
					pickups[n - 1] = 0;
					for(int i = n - 2; i > -1; i--) {
						if(collectCnt - pickups[i] > 0) {
							collectCnt -= pickups[i];
							pickups[i] = 0;
						} else {
							pickups[i] -= collectCnt;
							break;
						}
					}
				} else pickups[n - 1] -= cap;
				
			} else n--; //먼 곳을 갈 필요 없으니 줄인다.
		}
		
        
        return ans;
    }
}