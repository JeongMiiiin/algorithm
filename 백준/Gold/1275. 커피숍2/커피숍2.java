import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 1275 - 커피숍 1
 * 
 * 주어지는 값
 * N : 수의 개수
 * Q : 턴의 개수
 * 두번째 줄 : 수들의 값
 * 세번째 줄 ~ Q + 2번째 줄 : 턴의 값
 * 턴 정보
 * x -> 구간 시작, y : 구간 종료, a : 변경할 인덱스, b : 변경할 값
 * 
 * 
 * 세그먼트 트리 
*/

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		//트리 생성
		SegmentTree tree = new SegmentTree(N);
		
		//초기값 받아오기
		long[] initArr = new long[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i <= N; i++) initArr[i] = Long.parseLong(st.nextToken());
		tree.init(initArr, 1, 1, N);
		
		int x, y, a;
		long b;
		for(int i=0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			if(x > y) {
				int temp = x;
				x = y;
				y = temp;
			}
			
			a = Integer.parseInt(st.nextToken());
			b = Long.parseLong(st.nextToken());
			
			bw.write(tree.sum(1, 1, N, x, y) + "\n");
			tree.updateDirect(1, 1, N, a, b);
		}
		
		bw.close();
	}
	
	static class SegmentTree {
		long[] tree;

		public SegmentTree(int n) {
			//트리 높이 계산
			double treeHei = Math.ceil(Math.log(n) / Math.log(2)) + 1;
			//트리 노드 수 계산
			long treeNodeCnt = Math.round(Math.pow(2, treeHei));
			
			this.tree = new long[Math.toIntExact(treeNodeCnt)];
		}
		
		long init(long[] arr, int node, int start, int end) {
			//리프 노드인 경우
			if(start == end) return tree[node] = arr[start];
			else return tree[node] = init(arr, node * 2, start, (start + end) / 2)
					                 + init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
		}
		
		long sum(int node, int start, int end, int left, int right) {
			if(end < left || right < start) return 0;
			else if(left <= start && end <= right) return tree[node];
			else return sum(node * 2, start, (start + end) / 2, left, right)
	                    + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		}
		
		long updateDirect(int node, int start, int end, int idx, long changeValue) {
			if(idx < start || end < idx) return tree[node];
			else if(start == idx && end == idx) return tree[node] = changeValue;
			else return tree[node] = updateDirect(node * 2, start, (start + end) / 2, idx, changeValue)
					 				 + updateDirect(node * 2 + 1, (start + end) / 2 + 1, end, idx, changeValue);
		}
	}
	
}