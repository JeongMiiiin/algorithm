import java.util.Stack;

/*
 * 프로그래머스 괄호변환
 * '('와 ')'로만 이루어진 문자열이 있을 경우, '('의 개수와 ')'의 개수가 같다면
 * 이를 균형잡힌 괄호 문자열
 * 여기에 '('와 ')'의 짝도 모두 맞을 경우에는 올바른 괄호 문자열
 * 올바른 괄호 문자열이 아닐 경우
 * 다음과 같이 변환
 * 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환
 * 2. 문자열 w를 두 "균형잡힌 괄호 문자열' u, v로 분리. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 함.
 * 3. 문자열 u가 "올바른 괄호 문자열"이라면 문자열 v에 대해 1단계부터 다시 수행
 * 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환
 * 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행
 * 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙임
 * 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙임
 * 4-3. ')'를 다시 붙임
 * 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열읠 괄호 방향을 뒤집어서 뒤에 붙임
 * 4-5. 생성된 문자열 반환
 * 
 * 
*/

class Solution {
    public String solution(String p) {
        //올바른 문자열인 경우
		if(checkProper(p)) return p;
		
        return properString(p);
    }
	
	private static String properString(String s) {
		char[] sList = s.toCharArray();
		
		int openUCnt = 0;
		int closeUCnt = 0;
		
		if(sList[0] == '(') openUCnt++;
		else closeUCnt++;
		
		int idx = 0;
		for(int i=1; i < sList.length; i++) {
			if(sList[i] == '(') openUCnt++;
			else closeUCnt++;
			
			//균형잡힌 문자열에 도달했을 경우
			if(openUCnt == closeUCnt) {
				idx = i;
				break;
			}
		}
		
		String answer = "";
		
		String u = s.substring(0, idx + 1);
		String v = s.substring(idx + 1, s.length());
		
		//u가 올바른 문자열인 경우 answer에 붙이기
		if(checkProper(u)) {
			answer += u;
			//v가 빈 문자열이 아닌 경우
			if(!v.equals("")) answer += properString(v);
		} else {
			answer += "(";
			if(!v.equals("")) answer += properString(v);
			answer += ")";
			
			u = u.substring(1, u.length() - 1);
			for(char c : u.toCharArray()) {
				if(c == '(') answer += ")";
				else answer += "(";
			}
		}
		
		return answer;
	}
	
	private static boolean checkProper(String s) {
		char[] sList = s.toCharArray();
		Stack<Character> check = new Stack<>();
		
		boolean flag = true;
		for(char c : sList) {
			if(c == '(') check.add(c);
			else {
				//스택이 비어있으면 올바른 문자열이 아니므로 false 후 종료
				if(check.isEmpty()) {
					flag = false;
					break;
				}
				//열기 괄호 없애기
				check.pop();
			}
		}
		
		return (flag && check.size() == 0);
	}
}