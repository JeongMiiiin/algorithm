import java.util.Arrays;

class Solution {
    //유저를 관리하기 위한 클래스
	static class User {
		int purchasePercent, limitPayment;
		public User(int purchasePercent, int limitPayment) {
			super();
			this.purchasePercent = purchasePercent;
			this.limitPayment = limitPayment;
		}
	}
	
	static int R;
	static User[] UserList;
	static int[][] dp;
	static int[] numbers, answer;
    public int[] solution(int[][] users, int[] emoticons) {
        R = emoticons.length;
		numbers = new int[R];
        answer = new int[2];
        UserList = new User[users.length];
        //최대 비율과 최저비율 구하기
        for(int i=0; i < users.length; i++) UserList[i] = new User(users[i][0], users[i][1]);
        
        //미리 비율에 따른 값 계산해두기
        dp = new int[5][emoticons.length];
        for(int i=10; i <= 40; i+= 10) {
        	for(int j=0; j < emoticons.length; j++) dp[i / 10][j] = (emoticons[j] / 100) * (100 - i);
        }
        
        dupliComb(0, 40);
        
        
        return answer;
    }
    
    private static void dupliComb(int cnt, int start) {
		if(cnt == R) { //조합이 완성되었을 때
			calc();
			return;
		}
		
		for(int i=start; i >= 10; i -= 10) {
			numbers[cnt] = i;
			dupliComb(cnt + 1, start);
		}
	}

	private static void calc() {
		int[] payment = new int[R];
		for(int i=0; i < R; i++) payment[i] = dp[numbers[i] / 10][i];
		
		int signUpCnt = 0, totalPayment = 0;
		//사용자들 계산
		outer : for(int i=0; i < UserList.length; i++) {
			int temp = 0;
			User cur = UserList[i];
			for(int j=0; j < payment.length; j++) {
				//구매할 수 있는 이모티콘의 할인율일 때
				if(cur.purchasePercent <= numbers[j]) temp += payment[j];
				
				//이모티콘 플러스 서비스 가입 조건에 해당
				if(temp >= cur.limitPayment) {
					signUpCnt++;
					continue outer;
				}
			}
			//이모티콘 가입 조건이 아니면 구매한 가격 더해주기
			totalPayment += temp;
		}
		
		
		//가입자가 현재 가입자보다 적을때 패스
		if(answer[0] > signUpCnt) return;
		
		//가입자가 기존보다 많을 때
		if(signUpCnt > answer[0]) answer[1] = totalPayment;
		//가입자가 기존이랑 같을 때
		else answer[1] = Math.max(answer[1], totalPayment);
		
		answer[0] = signUpCnt;
	}
}