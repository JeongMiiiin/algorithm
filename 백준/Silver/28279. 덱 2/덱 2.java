import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		Deque<Integer> deque = new ArrayDeque<>();
		for(int i=0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			switch(Integer.parseInt(st.nextToken())) {
				case 1 : 
					deque.addFirst(Integer.parseInt(st.nextToken()));
					break;
				case 2 :
					deque.addLast(Integer.parseInt(st.nextToken()));
					break;
				case 3 :
					sb.append((!deque.isEmpty() ? deque.pollFirst() : -1) + "\n");
					break;
				case 4 :
					sb.append((!deque.isEmpty() ? deque.pollLast() : -1) + "\n");
					break;
				case 5 : 
					sb.append(deque.size() + "\n");
					break;
				case 6 :
					sb.append((deque.isEmpty() ? 1 : 0) + "\n");
					break;
				case 7 :
					sb.append((!deque.isEmpty() ? deque.peekFirst() : -1) + "\n");
					break;
				case 8 : 
					sb.append((!deque.isEmpty() ? deque.peekLast() : -1) + "\n");
					break;
			}
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}