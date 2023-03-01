import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 * 백준 11866번 요세푸스 문제 0
*/
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		LinkedList<Integer> li = new LinkedList<Integer>();
		for(int i=1; i <= N; i++)
			li.add(i);
		
		List<Integer> yosei = new ArrayList<Integer>();
		int idx = K - 1;
		while(li.size() > 0) {
			if(idx >= li.size()) {
				idx %= li.size();
			}
			yosei.add(li.remove(idx));
			idx += K -1;
		}
		
		System.out.println("<"+ yosei.toString().substring(1,yosei.toString().length() - 1) + ">");
		
	}
}
