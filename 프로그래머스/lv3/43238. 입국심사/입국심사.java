/*
 * 프로그래머스 - 입국심사
 * 
 * 주어지는 값
 * n : 입국심사를 기다리는 사람 수
 * times : 각 심사관이 한 명을 심사하는데 걸리는 시간
 * 
 * n명이 입국심사를 위해 줄을 서서 기다리고 있음.
 * 각 입국심사대에 있는 심사관마다 심사하는데 걸리는 시간
 * 
 * 처음에 모든 심사대는 비어있음.
 * 한 심사대에서는 한명만 심사가 가능
 * 가장 앞에 서있는 사람은 비어있는 심사대로 가서 심사를 받을 수 있음.
 * 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사를 받을 수도 있음.
 * 모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고싶음.
 * 
 * 최소값의 배수 잡기 -> 최소값의 배수로 증가할때마다 
 * 
 * 처음 배수 -> n / times.length * 최소값
 * 
 * 처음 배수에서 값이 넘치는 배수를 찾는다. 값이 넘치는 배수와 -1 한 곳부터 이분탐색 시작
 * 
 * 
*/

class Solution {
    public long solution(int n, int[] times) {
        long max = 0;
		
		for(int num : times) max = Math.max(num, max);
		
		long start = 1;
		
		//이분탐색 출발시작
		long end = max * n;
		
		while(start < end) {
			long mid = (end - start) / 2 + start;
			
			if(checkComplete(mid, times) < n) start = mid + 1;
			else end = mid;
		}
		
		
        long answer = end;
        
        
        return answer;
    }
    
    private static long checkComplete(long start, int[] times) {
		long result = 0;
		for(int num : times) result += (start / num);
		
		return result;
	}
}