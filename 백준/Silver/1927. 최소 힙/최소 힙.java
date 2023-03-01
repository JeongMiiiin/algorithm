import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * 백준 11286번 절댓값 힘
*/
public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>((a,b) -> a - b);
		
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			if(n != 0) q.add(n); //0이 아닐 경우
			else {
				if( !q.isEmpty() ) sb.append(q.poll() + "\n");
				else sb.append("0\n");
			}
		}
		System.out.println(sb.toString());
	}
	
}