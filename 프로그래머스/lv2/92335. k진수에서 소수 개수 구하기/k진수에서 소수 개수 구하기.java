/*
 * 프로그래머스 - k진수에서 소수 개수 구하기
 * 
 * 양의정수 n이 주어짐.
 * 이 숫자를 k진수로 바꿨을 때, 변환된 수 안에 아래 조건에 맞는 소수가 몇 개인지 알아보려 함
 * 0P0처럼 소수 양쪽에 0이 있는 경우
 * P0처럼 소수 오른쪽에만 0이 있고, 왼쪽에는 아무것도 없는 경우
 * 0P처럼 소수 왼쪽에만 0이 있고, 오른쪽에는 아무것도 없는 경우
 * P처럼 소수 양쪽에 아무것도 없는 경우
 * 단, P는 각 자릿수에 0을 포함하지 않는 소수
 * 
 * 만들어야할 것
 * 1. 진수로 바꾸는 함수
 * 2. 소수를 판별할 함수
 * 진수로 바꾸기 -> 437674 / 3 -> 
 * 
*/

class Solution {
    
    public int solution(int n, int k) {
        String target = Integer.toString(n, k);
		
        int answer = 0;
        
        String s = "";
        for(int i=0; i < target.length(); i++) {
        	int targetNum = Integer.parseInt(target.substring(i, i + 1));
        	if(targetNum == 0) { //만난 숫자가 0일 때
        		//기존에 담겨있는 숫자들이 있고, 그 숫자들이 소수일 때 answer 증가
        		if( !s.equals("") && isPrime(Long.parseLong(s))) answer++;
        		s = ""; //담을 숫자 초기화
        	} else s += target.substring(i, i + 1);
        }
        
        if( !s.equals("") && isPrime(Long.parseLong(s))) answer++;
        
        return answer;
    }
    
    private static boolean isPrime(Long n) {
		if(n == 1) return false; //n이 1이면 false 리턴
		for(long i=2; i <= (long) Math.sqrt(n); i++) {
			if(n % i == 0) return false;
		}
		return true;
	}
}