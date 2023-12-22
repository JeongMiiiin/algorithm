import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Queue<Integer> q = new LinkedList<>();
		//카드 추가
		for(int i=1; i <= N; i++) q.add(i);
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()) {
			sb.append(q.poll() + " ");
			if(!q.isEmpty()) q.add(q.poll());
		}
		System.out.println(sb.toString());
		sc.close();
	}
}