import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Deque<Point> dq = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		for(int i=1; i <= N; i++) dq.add(new Point(i, Integer.parseInt(st.nextToken())));
		StringBuilder sb = new StringBuilder();
		while(dq.size() > 1) {
			Point cur = dq.poll();
			sb.append(cur.x + " ");
			boolean flag = cur.y > 0;
			int idx = 0;
			if(flag) while(++idx < cur.y) dq.add(dq.poll());
			else while(idx-- > cur.y) dq.addFirst(dq.pollLast());
		}
		sb.append(dq.poll().x);
		
		System.out.println(sb.toString());
		sc.close();
	}
}