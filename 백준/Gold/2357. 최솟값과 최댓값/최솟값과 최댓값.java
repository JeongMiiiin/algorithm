import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 2357 - 최솟값과 최댓값
 * 
 * 주어지는 값
 * N : 정수의 개수
 * M : 쌍의 개수
 * 두번째 줄 ~ N번째 줄 : 정수들의 수
 * N + 1번째 줄 ~ M번째 줄 : 쌍들의 수
 * a, b -> a와 b사이의 최댓값과 최솟값
*/

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		SegmentTree minTree = new SegmentTree(N, false);
		SegmentTree maxTree = new SegmentTree(N, true);
		long[] initArr = new long[N + 1];
		for(int i=1; i <= N; i++) initArr[i] = Long.parseLong(br.readLine());
		minTree.init(initArr, 1, 1, N);
		maxTree.init(initArr, 1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			long min = minTree.find(1, 1, N, a, b);
			long max = maxTree.find(1, 1, N, a, b);
			sb.append(min + " " + max + "\n");
		}
				
		//출력
		System.out.println(sb.toString());

		br.close();
	}
	
	
	static class SegmentTree {
		//세그먼트 트리를 구현할 배열
		private long[] tree;
		boolean flag; //true면 최대값 저장
		
		public SegmentTree(int n, boolean flag) {
			//트리 높이 계산
			double treeHei = Math.ceil(Math.log(n) / Math.log(2)) + 1;
			//트리 노드 수 계산
			long treeNodeCnt = Math.round(Math.pow(2, treeHei));	
			this.tree = new long[Math.toIntExact(treeNodeCnt)];
			this.flag = flag;
		}
		
		long init(long[] arr, int node, int start, int end) {
			//리프 노드인 경우
			if(start == end) return tree[node] = arr[start];
			else {
				if(flag) return tree[node] = Math.max(init(arr, node * 2, start, (start + end) / 2), init(arr, node * 2 + 1, (start + end) / 2 + 1, end));
				else return tree[node] = Math.min(init(arr, node * 2, start, (start + end) / 2), init(arr, node * 2 + 1, (start + end) / 2 + 1, end));
			}
		}
		
		long find(int node, int start, int end, long left, long right) {
			long def = Long.MAX_VALUE;
			if(flag) def = 0;
			
			//노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 속하지 않는 경우 리턴
			if(end < left || right < start) return def;
			//노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 속하는 경우 노드 값 리턴
			else if (left <= start && end <= right) return tree[node];
			/*
			 * 2가지 경우가 존재
			 * 1. 노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 일부는 속하고 일부는 속하지 않는 경우
			 * 2. 노드가 가지는 값의 구간이 구하려고 하는 합의 구간을 모두 포함하는 경우
			 * 이와 같은 경우에는 자식 노드를 탐색해서 값을 리턴
			*/
			else {
				if(flag) return Math.max(find(node * 2, start, (start + end) / 2, left, right), find(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
				else return Math.min(find(node * 2, start, (start + end) / 2, left, right), find(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
			}
		}
		
		//배열에 특정 인덱스의 값이 변경될 경우 세그먼트 트리의 노드 값 변경 (노드 값을 직접 변경)
		long updateDirect(int node, int start, int end, long idx, long changeValue) {
			//노드가 가지는 값의 구간에 변경될 인덱스가 포함되지 않을 경우
			if(idx < start || end < idx) return tree[node];
			//노드가 가지는 값의 구간과 변경될 인덱스 값이 같은 경우
			else if(start == idx && end == idx) return tree[node] = changeValue;
			//노드가 가지는 값의 구간에 변경될 인덱스가 포함되는 경우
			else {
				if(flag) return tree[node] = Math.min(updateDirect(node * 2, start, (start + end) / 2, idx, changeValue), updateDirect(node * 2 + 1, (start + end) / 2 + 1, end, idx, changeValue));
				else return tree[node] = Math.max(updateDirect(node * 2, start, (start + end) / 2, idx, changeValue), updateDirect(node * 2 + 1, (start + end) / 2 + 1, end, idx, changeValue));
			}
		}
	}
}