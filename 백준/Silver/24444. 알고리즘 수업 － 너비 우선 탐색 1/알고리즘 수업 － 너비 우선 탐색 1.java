import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		List<Integer>[] list = new ArrayList[N + 1];
		for(int i=1; i <= N; i++) list[i] = new ArrayList<>();
		
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list[start].add(end);
			list[end].add(start);
		}
		
		for(int i=1; i <= N; i++) Collections.sort(list[i]);
		
		int[] result = new int[N + 1];
		int cnt = 1;
		result[R] = cnt;
		Queue<Integer> q = new LinkedList<>();
		q.add(R);
		
		int cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			for(int j : list[cur]) {
				if(result[j] == 0) { //방문하지 않은 경우
					result[j] = ++cnt;
					q.add(j);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i <= N; i++) sb.append(result[i] + "\n");
		
		System.out.println(sb.toString());
		br.close();
	}
}