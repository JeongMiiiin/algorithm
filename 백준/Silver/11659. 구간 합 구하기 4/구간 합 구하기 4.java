import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 11659 - 구간 합 구하기 4
 * 
 * 주어지는 값
 * N : 수의 개수
 * M : 구간의 합을 구하는 횟수
 * 둘째줄  : N개의 수의 값
 * 3 ~ (M + 1)번째 줄 : 구하고자 하는 구간에 대한 정보
 * a : 시작구간
 * b : 종료구간 
 * 
 * N개의 수가 주어짐. 어떤 부분의 합을 구하려 한다.
*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] origin = new long[N + 1];
		FenwickTree tree = new FenwickTree(N + 1);
		
		st = new StringTokenizer(br.readLine(), " ");
		//업데이트
		for(int i=1; i <= N; i++) {
			origin[i] = Integer.parseInt(st.nextToken());
			tree.update(i, origin[i]);
		}
		
		StringBuilder sb = new StringBuilder();
		//변경 혹은 구간 합 구하기
		int a, b;
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			sb.append(tree.getRange(a, b) + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static class FenwickTree {
		public long[] data;
 
		public FenwickTree(int n) {
			data = new long[n];
		}
		
		public void update(int idx, long value) {
			while(idx < data.length) {
				data[idx] += value;
				idx += (idx & -idx);
			}
		}
		
		public long sum(int idx) {
			long result = 0;
			while(idx > 0) {
				result += data[idx];
				idx -= (idx & -idx);
			}
			return result;
		}
		
		public long getRange(int start, int end) {
			return sum(end) - sum(start - 1);
		}
	}
	
}