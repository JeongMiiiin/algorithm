import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

// 백준 4949번 균형잡힌 세상
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		Stack<Integer> stack = new Stack<Integer>();
		while(true) {
			char[] target = br.readLine().toCharArray();
			if(target[0] == '.') break; //기저 조건
			String result = "yes";
			outer : for(char c : target) {
				if(c == ' ') continue;
				switch(c) {
				case '(' :
					stack.push(1);
					break;
				case ')' :
					if(stack.isEmpty() || stack.pop() == 2) {
						result = "no";
						break outer;
					}
					break;
				case '[' :
					stack.push(2);
					break;
				case ']' :
					if(stack.isEmpty() || stack.pop() == 1) {
						result = "no";
						break outer;
					}
					break;
				}
			}
			
			if( !stack.isEmpty() ) result = "no";
			
			System.out.println(result);
			stack.clear();
		}
	}
}
