import java.util.Scanner;

/*
 * 백준 10868 - 최솟값
 * 
 * 주어지는 값
 * N : 수의 개수
 * M : 쌍의 개수
 * 
 * N개의 정수가 주어짐
 * a ~ b 쌍에서 가장 최솟값을 찾아야 함
 * 
 * 세그먼트 트리로 최솟값을 저장
*/
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		//트리 생성
		SegmentTree tree = new SegmentTree(N);
		long[] initArr = new long[N + 1];
		for(int i=1; i <= N; i++) initArr[i] = sc.nextLong();
		tree.init(initArr, 1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		//변경 혹은 구간 합 구하기
		long a, b;
		for(int i=0; i < M; i++) {
			a = sc.nextLong();
			b = sc.nextLong();
			sb.append(tree.findMin(1, 1, N, a, b) + "\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
	
	static class SegmentTree {
		//세그먼트 트리를 구현할 배열
		private long[] tree;

		//생성자에서 세그먼트 트리의 전체노드 수 계산 
		public SegmentTree(int n) {
			//트리 높이 계산
			double treeHei = Math.ceil(Math.log(n) / Math.log(2)) + 1;
			//트리 노드 수 계산
			long treeNodeCnt = Math.round(Math.pow(2,  treeHei));
			//트리 길이 실정
			tree = new long[Math.toIntExact(treeNodeCnt)];
		}
		
		/*
		 * arr -> 세그먼트 트리로 나타낼 배열, node -> 메서드를 시작할 노드 인덱스
		 * start -> 노드들이 가지는 값의 시작 인덱스, end -> 노드들이 가지는 값의 종료 인덱스
		 * 자식 노드 번호 (좌측) = 부모 노드 번호 * 2
		 * 자식 노드 번호 (우측) = (부모 노드 번호 * 2) + 1
		*/
		long init(long[] arr, int node, int start, int end) {
			//세그먼트 트리의 리프노드인 경우
			//리프 노드에 배열의 값 저장 후 리턴
			if(start == end) return tree[node] = arr[start]; 
			//세그먼트 트리의 리프노드가 아닌 경우
			// 자식 노드의 값의 최소값을 담고 리턴
			else return tree[node] = Math.min(init(arr, node * 2, start, (start + end) / 2), init(arr, node * 2 + 1, (start + end) / 2 + 1, end)); 
		}
		
		long findMin(int node, int start, int end, long left, long right) {
			//노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 속하지 않는 경우 0리턴
			if(end < left || right < start) return Long.MAX_VALUE;
			//노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 속하는 경우 노드 값 리턴
			else if (left <= start && end <= right) return tree[node];
			/*
			 * 2가지 경우가 존재
			 * 1. 노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 일부는 속하고 일부는 속하지 않는 경우
			 * 2. 노드가 가지는 값의 구간이 구하려고 하는 합의 구간을 모두 포함하는 경우
			 * 이와 같은 경우에는 자식 노드를 탐색해서 값을 리턴
			*/
			else return Math.min(findMin(node * 2, start, (start + end) / 2, left, right), findMin(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
		}
		
		//배열에 특정 인덱스의 값이 변경될 경우 세그먼트 트리의 노드 값 변경 (노드 값을 직접 변경)
		long updateDirect(int node, int start, int end, long idx, long changeValue) {
			//노드가 가지는 값의 구간에 변경될 인덱스가 포함되지 않을 경우
			if(idx < start || end < idx) return tree[node];
			//노드가 가지는 값의 구간과 변경될 인덱스 값이 같은 경우
			else if(start == idx && end == idx) return tree[node] = changeValue;
			//노드가 가지는 값의 구간에 변경될 인덱스가 포함되는 경우
			else return tree[node] = Math.min(updateDirect(node * 2, start, (start + end) / 2, idx, changeValue), updateDirect(node * 2 + 1, (start + end) / 2 + 1, end, idx, changeValue));
		}
	}
}