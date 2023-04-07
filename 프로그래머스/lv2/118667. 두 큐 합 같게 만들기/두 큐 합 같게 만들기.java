import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static int[] q1, q2;
	static int[] numbers = new int[2];
	static int N, ans;
    public int solution(int[] queue1, int[] queue2) {
        //초기화
		ans = Integer.MAX_VALUE;
		N = queue1.length;
		q1 = new int[N];
		q2 = new int[N];
		//static 변수로 사용하기 위한 깊은 복사
		System.arraycopy(queue1, 0, q1, 0, N);
		System.arraycopy(queue2, 0, q2, 0, N);
		
		long total = 0;
		int q1Max = 0, q2Max = 0;
		for(int i=0; i < N; i++) {
			total += queue1[i] + queue2[i];
			q1Max = Math.max(queue1[i], q1Max);
			q2Max = Math.max(queue2[i], q2Max);
		}
		
		//최종합이 짝수일 때 그리고 제일 큰 수가 최종 합의 2분의 1보다 작거나 같을 때 실행 (나눠질 수 있을 때)
		if(total % 2 == 0 && total / 2 >= q1Max && total / 2 >= q2Max) dupliPerm(0);
		
		if(ans == Integer.MAX_VALUE) ans = -1;
		
        return ans;
    }
    
    private static void dupliPerm(int cnt) {
		if(cnt == 2) {
			simulate();
			return;
		}
		
		for(int i=0; i < (N * 2 - 1); i++) {
			numbers[cnt] = i;
			dupliPerm(cnt + 1);
		}
	}
	
	private static void simulate() {
		int cnt = numbers[0] + numbers[1];
		/*
		 * 1. 정해진 액션이 지금 액션의 최소횟수보다 작음
		 * 2. 액션의 횟수가 (N * 2 - 1)보다 클때
		*/
		if(ans < cnt || cnt > N * 2 - 1) return; 
		Queue<Integer> testq1 = new LinkedList<>();
		Queue<Integer> testq2 = new LinkedList<>();
		
		long q1Total = 0, q2Total = 0;
		for(int i=0; i < N; i++) {
			q1Total += q1[i];
			q2Total += q2[i];
			testq1.offer(q1[i]);
			testq2.offer(q2[i]);
		}
		
		//
		if(numbers[0] <= numbers[1]) {
			//첫번째에 일어나는 액션
			int q1Idx = 0;
			while(q1Idx++ < numbers[0]) {
				int target = testq1.poll();
				q1Total -= target;
				q2Total += target;
				testq2.offer(target);
			}
			
			int q2Idx = 0;
			while(q2Idx++ < numbers[1]) {
				if(testq2.isEmpty()) return; //진행할 수 없는 순열일 때
				int target = testq2.poll();
				q2Total -= target;
				q1Total += target;
				testq1.offer(target);
			}
		} else {
			int q2Idx = 0;
			while(q2Idx++ < numbers[1]) {
				int target = testq2.poll();
				q2Total -= target;
				q1Total += target;
				testq1.offer(target);
			}
			int q1Idx = 0;
			while(q1Idx++ < numbers[0]) {
				if(testq1.isEmpty()) return; //진행할 수 없는 순열일 때
				int target = testq1.poll();
				q1Total -= target;
				q2Total += target;
				testq2.offer(target);
			}
		}
		
		if(q1Total == q2Total) ans = Math.min(ans, cnt);
		
	}
}