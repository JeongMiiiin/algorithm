import java.util.Scanner;

/*
 * 백준 2042 - 구간 합 구하기
 * 
 * 주어지는 값
 * N : 수의 개수
 * M : 수의 변경이 일어나는 횟수
 * K : 구간의 합을 구하는 횟수
 * 둘째줄 ~ N + 1번째 줄 : N개의 수의 값
 * N + 2 ~ (N + M + K + 1)번째 줄 : 변경 혹은 구간에 대한 정보
 * a : 1 -> 변경 정보, 2 -> 구간 합 정보
 * b : 변경정보 시 바꿔야 하는 인덱스, 구간 합 정보시 구간 시작의 인덱스
 * c : 변경정보 시 바꾸는 값, 구간 합 정보시 구간 종료의 인덱스 
 * 
 * N개의 수가 주어짐. 중간에 수의 변경이 빈번히 일어나고, 그 중간에 어떤 부분의 합을 구하려 한다.
*/

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		
		long[] origin = new long[N + 1];
		FenwickTree tree = new FenwickTree(N + 1);
		
		//업데이트
		for(int i=1; i <= N; i++) {
			origin[i] = sc.nextLong();
			tree.update(i, origin[i]);
		}
		
		
		StringBuilder sb = new StringBuilder();
		//변경 혹은 구간 합 구하기
		long a, b, c;
		for(int i=0; i < M + K; i++) {
			a = sc.nextLong();
			b = sc.nextLong();
			c = sc.nextLong();
			//변경 시
			if(a == 1) {
				tree.update((int) b, c - origin[(int) b]);
				origin[(int) b] = c;
			}
			//구간합 구하기
			else sb.append(tree.getRange((int) b, (int) c) + "\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
	
	static class FenwickTree {
		//세그먼트 트리를 구현할 배열
		public long[] data;

		//생성자에서 세그먼트 트리의 전체노드 수 계산 
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