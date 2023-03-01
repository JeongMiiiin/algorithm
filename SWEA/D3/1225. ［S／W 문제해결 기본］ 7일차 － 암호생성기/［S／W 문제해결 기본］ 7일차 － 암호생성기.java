import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int t=1; t <=10; t++) {
			sc.nextInt();
			Queue<Integer> q1 = new ArrayDeque<Integer>();
			
			for(int i=0; i < 8; i++)
				q1.offer(sc.nextInt());
			int top = q1.peek();
			
			while(top > 0) {
				//사이클
				for(int i=1; i <= 5; i++) {
					top = q1.poll() - i;
					if(top <= 0) top = 0;
					q1.offer(top);
					if(top <= 0) break;
				}
					
			}
			
			StringBuilder sb = new StringBuilder();
			int size = q1.size();
			for(int i=0; i < size; i++)
				sb.append(q1.poll() + " ");
			
			System.out.println("#" + t + " " + sb.toString());
		}
	}
}
