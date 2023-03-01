import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

// 백준 - 9012번 괄호
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception{
		int T = Integer.parseInt(br.readLine());
		Stack<String> stack = new Stack<String>();
		for(int t=0; t < T; t++) {
			String result = "YES";
			String target = br.readLine();
			for(int i=0; i < target.length(); i++) {
				String s = target.substring(i, i+1);
				if(s.equals("("))
					stack.push(s);
				else {
					if( stack.isEmpty() ) result = "NO";
					else stack.pop();
				}
					
			}
			if( !stack.isEmpty() ) result = "NO";
			System.out.println(result);
			stack.clear();
		}
		
	}
}
