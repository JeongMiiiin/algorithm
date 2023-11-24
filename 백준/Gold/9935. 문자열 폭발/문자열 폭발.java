import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		char[] initList = br.readLine().toCharArray(); 
		char[] target = br.readLine().toCharArray();
		Stack<Character> stack1 = new Stack<>();
		Stack<Character> stack2 = new Stack<>();
		for(int i=0; i < initList.length; i++) {
			int lastIdx = target.length - 1;
			stack1.add(initList[i]);
			while(!stack1.isEmpty() && target.length <= stack1.size() + stack2.size() && lastIdx >= 0 && stack1.peek() == target[lastIdx]) {
				lastIdx--;
				stack2.add(stack1.pop());
			}
			//문자열이 완성되지 않았을 때
			if(lastIdx >= 0) {
				while(!stack2.isEmpty()) stack1.add(stack2.pop());
			} else stack2.clear();
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(char c : stack1) sb.append(c);
		
		
		bw.write(sb.length() > 0 ? sb.toString() : "FRULA");
		bw.close();
		br.close();
	}
}