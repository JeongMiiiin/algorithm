import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(sc.nextLine());
		for(int i=0; i < N; i++) {
			
			Stack<Character> left = new Stack<>();
			Stack<Character> right = new Stack<>();
			for(char c : sc.nextLine().toCharArray()) {
				switch(c) {
					case '<' :
						if(!left.isEmpty()) right.push(left.pop());
						break;
					case '>' :
						if(!right.isEmpty()) left.push(right.pop());
						break;
					case '-' :
						if(!left.isEmpty()) left.pop();
						break;
					default :
						left.push(c);
						break;
				}
			}
			StringBuilder temp = new StringBuilder();
			while(!left.isEmpty()) right.push(left.pop());
			while(!right.isEmpty()) temp.append(right.pop());
			sb.append(temp.toString() + "\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}