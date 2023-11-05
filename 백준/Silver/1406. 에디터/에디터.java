import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

//1406번
public class Main {

	public static void main(String[] args) throws IOException{
		
		//2 stack을 이용한 구현
		//스택은 모든 연산이 O(1)의 시간 복잡도를 가지기 때문에 시간초과에 걸리지 않는다고 함
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String origin[] = br.readLine().split("");
		
		Stack<String> leftStack = new Stack<String>();
		for(int i=0;i<origin.length;i++) {
			leftStack.push(origin[i]);
		}
		
		Stack<String> rightStack = new Stack<String>();
		
		int n = Integer.parseInt(br.readLine());
		while(n-- >0) {
			String command[] = br.readLine().split(" ");
			
			if(command[0].equals("P")) {
				leftStack.push(command[1]);
			}else if(command[0].equals("L")) {
				if(leftStack.size()>0) {
					rightStack.push(leftStack.pop());
				}
			}
			else if(command[0].equals("D")) {
				if(rightStack.size()>0) {
					leftStack.push(rightStack.pop());
				}
			}
			else if(command[0].equals("B")) {
				if(leftStack.size()>0) {
					leftStack.pop();
				}
			}
		}
		
		//여기에서 System.out.print 쓰면 시간초과
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while(!leftStack.isEmpty()) {
			rightStack.push(leftStack.pop());
		}
		while(!rightStack.isEmpty()) {
			bw.write(rightStack.pop());
		}
		bw.flush();
		bw.close();
		
	}

}