import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * SWEA 7465 - 창용 마을 무리의 개수
 * 주어지는 값
 * N : 사람 수
 * M : 관계의 수
 * 창용 마을에는 N명의 사람이 존재 (편의상 1 - N번까지 이름 부여)
 * 서로 알고 있는 관계면 하나의 무리라고 칭함
 * 무리의 개수를 출력하라
*/
public class Solution {
	static int[] P;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			makeGroup(N);
			
			int M = Integer.parseInt(st.nextToken());
			for(int i=0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(a != b) union(a, b);
			}
			
			Set<Integer> hs = new HashSet<Integer>();
			for(int i=1; i < P.length; i++) hs.add(P[i]);
			
			//출력
			bw.write("#" + t + " " + hs.size() + "\n");
		}
		bw.close();
	}
	
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return;
		checkGroup(bRoot, aRoot);
	}

	private static int find(int n) {
		if(P[n] == n) return n;
		return P[n] = find(P[n]);
	}

	private static void makeGroup(int n) {
		P = new int[n + 1];
		for(int i=1; i <= n; i++) P[i] = i;
	}
	
	private static void checkGroup(int prev, int cur) {
		for(int i=1; i < P.length; i++) {
			if(P[i] == prev) P[i] = cur;
		}
	}
	
}
