import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/*
 * 백준 12852 - 1로 만들기 2
 * 
 * 주어지는 값
 * X : 나눠야할 정수
 * 
 * 정수 X에 사용할 수 있는 연산은 3가지
 * 1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * 2. X가 2로 나누어 떨어지면, 2로 나눈다.
 * 3. 1을 뺀다.
 * 
 * 연산 3개를 적절히 사용해 1로 만들려고 할 때, 연산 횟수의 최솟값을 출력
*/
public class Main {
	static class divideNum {
		int n, operation;
		StringBuilder sb;
		public divideNum(int n, int operation, StringBuilder sb) {
			this.n = n;
			this.operation = operation;
			this.sb = sb;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		boolean[] visited = new boolean[N + 1];
		
		Queue<divideNum> q = new ArrayDeque<>();
		q.offer(new divideNum(N, 0, new StringBuilder(String.valueOf(N))));
		
		visited[N] = true;
		
		int ans = 0;
		StringBuilder sb = new StringBuilder();
		divideNum d;
		while( !q.isEmpty() ) {
			d = q.poll();
			if(d.n == 1) {
				ans = d.operation;
				sb = d.sb;
				break;
			}
			
			if(d.n % 3 == 0 && !visited[d.n / 3]) {
				q.offer(new divideNum(d.n / 3, d.operation + 1, new StringBuilder(d.sb.toString() + " " + String.valueOf(d.n / 3))));
				visited[d.n / 3] = true;
			}
			if(d.n % 2 == 0 && !visited[d.n / 2]) {
				q.offer(new divideNum(d.n / 2, d.operation + 1, new StringBuilder(d.sb.toString() + " " + String.valueOf(d.n / 2))));
				visited[d.n / 2] = true;
			}
			
			if( !visited[d.n - 1] ) {
				visited[d.n - 1] = true;
				q.offer(new divideNum(d.n - 1, d.operation + 1, new StringBuilder(d.sb.toString() + " " + String.valueOf(d.n - 1))));
			}
		}
		
		System.out.println(ans);
		System.out.println(sb.toString());
		
		sc.close();
	}
}