import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	
	public static void main(String[] args) throws Exception{
		int T = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<Integer>();
		StringBuilder sb = new StringBuilder();
		for(int t=0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			switch(command) {
				case "push" :
					stack.push( Integer.parseInt(st.nextToken()) );
					break;
				case "pop" :
					if( !stack.isEmpty() ) System.out.println(stack.pop());
					else System.out.println(-1);
					break;
				case "size" :
					System.out.println(stack.size());
					break;
				case "empty" :
					if( !stack.isEmpty() ) System.out.println(0);
					else System.out.println(1);
					break;
				case "top" :
					if( !stack.isEmpty() ) System.out.println(stack.peek());
					else System.out.println(-1);
					break;
			}
		}
	}
}
