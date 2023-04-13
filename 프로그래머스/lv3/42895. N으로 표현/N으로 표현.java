import java.util.ArrayList;
import java.util.List;

/*
 *  프로그래머스 - N으로 표현
 *  
 *  숫자 N(1 - 9)과 number(1 - 32000)가 주어질 때
 *  N과 사칙연산만 사용해서 표현할 수 있는 방법 중 N 사용횟수의 최솟값을 리턴
 *  최솟값이 8보다 크면 -1 리턴
 *  
 *  완탐?
 *  숫자와 숫자 사이에 가능한 경우
 *  1. 더하기
 *  2. 빼기
 *  3. 곱하기
 *  4. 나누기
 *  5. 자리수로 합치기
 *  5가지 경우로 나눠서 가는데, 9개 사용시 return
 *  값이 N과 같으면 최소값 업데이트
 *  
 *  사용개수로 만들 수 있는 값들을 나열
 *  -> 만들면서 걸리면 가장 최적이기 때문에 해당 내용으로 리턴
 *  -> 안 만들어지면 사용 개수가 적은 애들끼리 계산해보기
 *  
 *  dp[9]Map
 *  i : 사용개수
 *  j : 연산한 내역  
 *  
 *  1개 사용 -> 1개
 *  2개 사용 -> 1개 + 1개, 1개 * 1개, 1개 / 1개, 1개 - 1개, (1개1개)
 *  3개 사용 -> (1개 + 1개) + 1개, 
 *  
*/

class Solution {
    static int num, target, ans = 9;
	static boolean flag;
	static List<Integer>[] dp = new ArrayList[9];
    public int solution(int N, int number) {
        //초기화
		num = N;
		flag = false;
		for(int i=0; i < 9; i++) dp[i] = new ArrayList<>();
		//초기값 설정
		dp[1].add(num);
		
		//타겟넘버 설정
		target = number;
		
		if(num == target) return 1;
		
		calcDP();
		
		dfs(0, 0);
		
		//값을 만들 수 없을 때
		if(ans == 9) ans = -1;
        return ans;
    }
	
	//만들 때 값이 안 만들어졌으면 각각의 값들 계산해보기
	private static void dfs(int cnt, int sum) {
		if(cnt > 8) return; //8개 이상 사용 시 리턴
		
		//계산값이 일치할 때
		if(sum == target) {
			ans = Math.min(ans, cnt); //최소값으로 업데이트
			return;
		}
		
		for(int i=1; i < 9; i++) {
            if(cnt + i > ans) return;
			List<Integer> numList = dp[i];
			for(int j=0; j < numList.size(); j++) {
				dfs(cnt + i, sum + numList.get(j)); //더하기로 보내기
				dfs(cnt + i, sum - numList.get(j)); //빼기로 보내기
				if(numList.get(j) != 0) {
					dfs(cnt + i, sum * numList.get(j)); //곱하기로 보내기
					dfs(cnt + i, sum / numList.get(j)); //나누기로 보내기
				}
			}
		}
		
	}
    
    //dp 채우기
	private static void calcDP() {
		for(int i=2; i <= 8; i++) { //2개 이상 사용하는 값들 추가
			List<Integer> prevList = dp[i - 1];
			for(int j=0; j < prevList.size(); j++) {
				int prev = prevList.get(j);
				int plus = prev + num;
				int minus = prev - num;
				int multiple = prev * num;
				int divide = prev / num;
				
				//수가 일치할 때
				if(plus == target || minus == target || multiple == target || divide == target) {
					ans = i;
					return;
				}
				
				//값 추가
				dp[i].add(plus);
				dp[i].add(minus);
				dp[i].add(multiple);
				dp[i].add(divide);
			}
			
			int digit = digitNum(i);
			//수가 일치할 때
			if(digit == target) {
				ans = i;
				return;
			}
			dp[i].add(digit);
		}
	}
	
	//자리수로 합치는 함수
	private static int digitNum(int cnt) {
		String s = "";
		for(int i=0; i < cnt; i++) s += Integer.toString(num);
		return Integer.parseInt(s);
	}
}