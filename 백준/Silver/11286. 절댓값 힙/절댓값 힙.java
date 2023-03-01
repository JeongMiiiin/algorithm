import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 백준 11286번 절댓값 힘
*/
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		PriorityQueue<Integer> q = new PriorityQueue<>((a,b) -> Math.abs(a) == Math.abs(b) ? a -b : Math.abs(a) - Math.abs(b));
		
		for(int t=0; t < T; t++) {
			int n = sc.nextInt();
			if(n != 0) q.add(n); //0이 아닐 경우
			else {
				if( !q.isEmpty() ) System.out.println(q.poll());
				else System.out.println(0);
			}
		}
		
		sc.close();
		
	}
	
}
