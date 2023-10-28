import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 백준 17298 - 오큰수
 * 크기가 N인 수열
 * 수열의 각 원소 Ai에 대해서 오큰수 NGE(i)를 구하려고 한다. Ai의 오큰수는 오른쪽에 있으면서 Ai보다 큰 수 중에서 가장 왼쪽에 있는 수를 의미
 * 단, 그러한 수가 없다면 -1
 * */

public class Main {
	static class Param {
		int val, idx;
		public Param(int val, int idx) {
			this.val = val;
			this.idx = idx;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] resultList = new int[N];
		Arrays.fill(resultList, -1);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Stack<Param> stack = new Stack<>();
		
		int curVal;
		Param prev;
		for (int i = 0; i < N; i++) {
			curVal = Integer.parseInt(st.nextToken());
			while(!stack.isEmpty() && stack.peek().val < curVal) {
				 prev = stack.pop();
				 resultList[prev.idx] = curVal;
			}
			stack.add(new Param(curVal, i));
        }
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N - 1; i++) sb.append(resultList[i] + " ");
		sb.append(-1);
		
		System.out.println(sb.toString());
	}
}