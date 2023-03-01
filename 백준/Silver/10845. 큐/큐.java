import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		Queue<Integer> q = new ArrayDeque<Integer>();
		int tail = -1;
		outer : for(int t=0; t < T; t++) {
			int res = -1;
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			switch(cmd) {
				case "push" :
					res = Integer.parseInt(st.nextToken());
					q.offer(res);
					tail = res;
					continue outer;
				case "pop" :
					if( !q.isEmpty() ) res = q.poll();
					break;
				case "size" :
					res = q.size();
					break;
				case "empty" :
					res = q.isEmpty() ? 1 : 0;
					break;
				case "front" :
					if( !q.isEmpty() ) res = q.peek();
					break;
				case "back" :
					if( !q.isEmpty() ) res = tail;
					break;
			}
			System.out.println(res);
		}
	}
}
