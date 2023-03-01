import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 3289 - 서로소 집합
 * 주어지는 값
 * T : 테스트 케이스 수
 * N : 초기 집합의 개수
 * M : 연산의 개수
 * 연산의 종류
 * 0 a b -> a가 포함되어 있는 집합과, b가 포함되어있는 집합을 합친다
 * 1 a b -> 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산
 * 각 테스트케이스마다 1로 시작하는 입력에 대해서 같은 집합에 속해있다면 1을, 아니면 0을 순서대로 한줄에 연속하여 출력
*/
public class Solution {
	static int N;
	static int[] P;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb;
		StringTokenizer st;
		
		for(int t=1; t <=T; t++) {
			sb = new StringBuilder();
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			
			makeGroup();
			
			int M = Integer.parseInt(st.nextToken());
			for(int i=0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(a == b) {
					if(cmd == 1) sb.append(1);
				} else {
					if(cmd == 0) union(a, b); //합쳐야할 때
					else sb.append(checkSame(a, b)); //체크해야할 때
				}
				
				
			}
			
			//출력
			System.out.println("#" + t + " " + sb.toString());
		}
	}

	private static void makeGroup() {
		P = new int[N + 1];
		for(int i=1; i <=N; i++) P[i] = i;
	}

	private static int checkSame(int a, int b) {
		if(find(a) == find(b)) return 1;
		return 0;
	}

	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return;
		P[bRoot] = aRoot;
		
	}

	private static int find(int n) {
		if(P[n] == n) return n;
		return P[n] = find(P[n]);
	}
}