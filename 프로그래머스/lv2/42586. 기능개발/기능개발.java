import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
    static class Work{
		int progress, speed;

		public Work(int progress, int speed) {
			this.progress = progress;
			this.speed = speed;
		}
	}
    
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Work> q = new ArrayDeque<>();
		for(int i=0; i < progresses.length; i++) q.offer(new Work(progresses[i], speeds[i]));
		List<Integer> list = new ArrayList<>();
		Work cur;
		while(!q.isEmpty()) {
			int cnt = 0, front = 0, size = q.size();
			for(int i=0; i < size; i++) {
				cur = q.poll();
				//개발 완료
				if(cur.progress + cur.speed >= 100) {
					if(front == 0) { //앞에 개발들이 끝난 상황일 때
						cnt++;
						continue;
					}
					//앞에 개발들이 끝난 상황이 아닐 때
					q.offer(cur);
				} else { // 개발 미완료
					front++;
					q.offer(new Work(cur.progress + cur.speed, cur.speed));
				}
				
			}
			if(cnt > 0) list.add(cnt);
		}
		
		int[] answer = new int[list.size()];
		
		for(int i=0; i < list.size(); i++) answer[i] = list.get(i);
		
        return answer;
    }
}