import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N, M, cnt = 1;
	static TreeSet<Integer>[] arr;
	static int[] result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		result = new int[N + 1];
		arr = new TreeSet[N + 1];
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(arr[start] == null) arr[start] = new TreeSet<>();
			arr[start].add(end);
			if(arr[end] == null) arr[end] = new TreeSet<>();
			arr[end].add(start);
		}
		find(R);
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i <= N; i++) {
			sb.append(result[i] + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	private static void find(int cur) {
		if(cnt > N || result[cur] != 0) return;
		result[cur] = cnt++;
		
		if(arr[cur] != null) for(int next : arr[cur]) find(next);
	}
}