import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 프로그래머스 - 디스크 컨트롤러
 * 
 * 요청들의 배열이 들어옴.
 * 요청이 들어올 때 요청시점과 소요시간이 적혀있음.
 * 
 * 순서 - 요청 빠른 순, 소요시간 적은 순
 * 현재 시간에서 요청시간 + 소요시간이 제일 적은 애부터 세팅 
 * 
*/

class Solution {
    
    static class Job implements Comparable<Job>{
		int requestTime, takeTime;

		public Job(int requestTime, int takeTime) {
			this.requestTime = requestTime;
			this.takeTime = takeTime;
		}

		@Override
		public int compareTo(Job o) {
			return this.requestTime - o.requestTime != 0 ? this.requestTime - o.requestTime : this.takeTime - o.takeTime;
		}
		
	}
    
    public int solution(int[][] jobs) {
        PriorityQueue<Job> queue = new PriorityQueue<>();
		
		//초기 세팅
		for(int[] temp : jobs) queue.add(new Job(temp[0], temp[1]));
		
		int answer = 0;
		int time = 0;
		int min = Integer.MAX_VALUE;
		Queue<Job> temp = new LinkedList<>();
		while(!queue.isEmpty()) {
			temp.clear();
			int takeTime = 1001; 
			min = Integer.MAX_VALUE;
			Job cur;
			//현재 시간에서 실행가능한 시간인 경우
			while(!queue.isEmpty() && time >= queue.peek().requestTime) {
				cur = queue.poll();
				if(takeTime > cur.takeTime) {
					min = cur.takeTime + (time - cur.requestTime);
					takeTime = cur.takeTime;
				}
				
				temp.add(cur);
			}
			
			//현재 처리할 작업이 없는 경우
			if(min == Integer.MAX_VALUE) {
				time = queue.peek().requestTime;
				continue;
			}
			
			int idx = 0;
			
			while(!temp.isEmpty()) {
				cur = temp.poll();
				//선택한 요청인 경우 queue에 다시 담지 않고 패스
				if(idx == 0 && cur.takeTime == takeTime) {
					idx++;
					continue;
				}
				
				queue.add(cur);
			}
			
			time += takeTime;
			answer += min;
		}
		
        return answer / jobs.length;
    }
}