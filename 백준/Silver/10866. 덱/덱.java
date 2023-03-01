import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		outer : for(int t=0; t < T; t++) {
			int ans = -1;
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			switch(cmd) {
				case "push_front" :
					q.addFirst(Integer.parseInt(st.nextToken()));
					continue outer;
				case "push_back" :
					q.addLast(Integer.parseInt(st.nextToken()));
					continue outer;
				case "pop_front" :
					if( !q.isEmpty() ) ans = q.pollFirst();
					break;
				case "pop_back" :
					if( !q.isEmpty() ) ans = q.pollLast();
					break;
				case "size" :
					ans = q.size();
					break;
				case "empty" :
					ans = q.isEmpty() ? 1 : 0;
					break;
				case "front" :
					if( !q.isEmpty() ) ans = q.peekFirst();
					break;
				case "back" :
					if( !q.isEmpty() ) ans = q.peekLast();
					break;
				
			}
			System.out.println(ans);
		}
	}
}
