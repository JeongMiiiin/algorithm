import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 백준 1874번 - 스택수열
 * 주어지는 값
 * N : 주어지는 수열의 크기
 * 2번째줄부터 N번째 줄 : 출력해야 하는 시나리오
 * 출력가능한 시나리오일 떄 push -> + pop -> - 로 표시
 * 출력 불가능할 때 NO로 표시
*/
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] senario = new int[N];
		for(int i=0; i < N; i++)
			senario[i] = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<Integer>();
		
		int idx = 0, n = 0, current = 0;
		boolean success = true;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N; i++) {
			n = senario[i];
			if(idx > n) { //스택에 넣은 값들이 지금 출력해야하는 값보다 클 때는 pop
				if(stack.isEmpty()) { //스택이 비어있으면 실패로 분류하고 탈출
					success = false;
					break;
				}
			} else {
				while(idx < n) {
					idx++;
					stack.push(idx);
					sb.append("+\n");
				}
			}
			
			current = stack.pop(); //가장 최근의 값 꺼내기
			if(n != current) {
				success = false;
				break;
			} else {
				sb.append("-\n");
			}
		}
		
		if(!success) {
			sb.setLength(0);
			sb.append("NO");
		}
		System.out.println(sb.toString());
	}
}
