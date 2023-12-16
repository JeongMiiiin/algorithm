import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int max = 1000000000;
		Scanner sc = new Scanner(System.in);
		long start = sc.nextLong();
		long end = sc.nextLong();
		if(start == end) { //이미 같을 때
			System.out.println(0);
			return;
		}
		boolean[] visited = new boolean[max + 1];
		Queue<Long> q = new LinkedList<>();
		visited[(int) start] = true;
		q.add(start);
		long result = 0, cur, n1, n2;
		outer : while(!q.isEmpty()) {
			result++;
			int size = q.size();
			for(int i=0; i < size; i++) {
				cur = q.poll();
				//1을 오른쪽에 추가
				n1 = cur * 10 + 1;
				if(n1 <= max && n1 > 0 && !visited[(int) n1]) {
					visited[(int) n1] = true;
					q.add(n1);
					if(n1 == end) break outer;
				}
				//2 곱하기
				n2 = cur * 2;
				if(n2 <= max && n2 > 0 && !visited[(int) n2]) {
					visited[(int) n2] = true;
					q.add(n2);
					if(n2 == end) break outer;
				}
			}
		}
		//만들 수 없는 경우
		if(!visited[(int) end]) result = -1;
		else result++;
		System.out.println(result);
	}
}