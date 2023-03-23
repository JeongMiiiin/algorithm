import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static class ArrayStack {
		int top, size;
		int [] stack;
		public ArrayStack(int size) {
			this.size = size;
			this.stack = new int[size];
			this.top = -1;
		}
		public void push(int item) {
			stack[++top] = item;
		}
		public int pop() {
			int pop = stack[top];
			stack[top--] = 0;
			return pop;
		}
		public int peek() {
			return stack[top];
		}
		public int size() {
			return top + 1;
		}
		public boolean empty() {
			return top + 1 == 0;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		String cmd;
		int target;
		ArrayStack customStack = new ArrayStack(n);
		for(int i=0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			cmd = st.nextToken();
			switch(cmd) {
				case "push":
					target = Integer.parseInt(st.nextToken());
					customStack.push(target);
					break;	
				case "pop":
					if(!customStack.empty()) bw.write(customStack.pop() + "\n");
					else bw.write(-1 + "\n");
					break;
				case "size":
					bw.write(customStack.size() + "\n");
					break;
				case "empty":
					if(customStack.empty()) bw.write(1 + "\n");
					else bw.write(0 + "\n");
					break;
				case "top":
					if(!customStack.empty()) bw.write(customStack.peek() + "\n");
					else bw.write(-1 + "\n");
					break;
			}
		}
		bw.close();
	}
}