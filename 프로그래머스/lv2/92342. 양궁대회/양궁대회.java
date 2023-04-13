class Solution {
    static int compareScore, N;
	static boolean flag; //우승이 가능한지 판단해주는 상태변수
	static int[] lionInfo, ans, apeachInfo;
    public int[] solution(int n, int[] info) {
        //초기화
		N = n;
		lionInfo = new int[11];
		ans = new int[11];
		flag = false;
		compareScore = 0;
		apeachInfo = info;
		
		dupliPerm(0);
		
		//우승이 불가능할 때
		if(!flag) {
			ans = new int[1];
			ans[0] = -1;
		}
        return ans;
    }
    
    private static void dupliPerm(int cnt) {
		if(!beyondNum()) return; //이미 안의 값이 n을 넘을 때
		
		//체크해야할 케이스가 완성되었을 때
		if(cnt == 11) {
			if(!checkNum()) return; //안의 값이 n과 일치하는지 확인
			compareScore();
			return;
		}
		
		for(int i=10; i >= 0; i--) {
			lionInfo[cnt] = i;
			dupliPerm(cnt + 1);
		}
	}

	private static void compareScore() {
		int apeach = 0;
		int lion = 0;
		
		for(int i=0; i <= 10; i++) {
			int possibleScore = 10 - i;
			//라이언쪽이 더 많이 맞춘 경우
			if(lionInfo[i] > apeachInfo[i]) lion += possibleScore;
			else if(lionInfo[i] <= apeachInfo[i] && (lionInfo[i] != 0 || apeachInfo[i] != 0)) apeach += possibleScore;
		}
		
		//라이언이 우승하는 경우
		if(lion > apeach) {
			if(!flag || lion - apeach > compareScore) { //처음 우승상태로 바꾸거나 기존 차이점수보다 더 큰 차이가 날 때 
				compareScore = lion - apeach;
				copyInfo();
			} else if(lion - apeach == compareScore) checkArr();
			
			flag = true; //우승 가능상태
			
			
		}
		
	}
	
	//더 작은지 확인
	private static void checkArr() {
		for(int i = ans.length-1; i >=0 ; i--) {
	        if(ans[i] == lionInfo[i]) continue;
	        if(ans[i] < lionInfo[i]) copyInfo();
	        break;
	    }
	}
	
	//더 작은 스코어 경우의 수인 경우 복사
	private static void copyInfo() {
		for(int i=0; i <= 10; i++) ans[i] = lionInfo[i];
	}

	private static boolean checkNum() {
		int cnt = 0;
		for(int i=0; i <= 10; i++) cnt += lionInfo[i];
		return (cnt == N);
	}

	//순열로 만들어지는 케이스의 값이 n을 넘어서는지 확인
	private static boolean beyondNum() {
		int cnt = 0;
		for(int i=0; i <= 10; i++) {
			cnt += lionInfo[i];
			if(cnt > N) return false;
		}
		return true;
	}
}