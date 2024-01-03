import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static List<Integer>[] islands;
	static long[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		arr = new long[N + 1];
		islands = new ArrayList[N + 1];
		for(int i=1; i <= N; i++) islands[i] = new ArrayList<>();
		
		StringTokenizer st;
		for(int i=2; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			boolean wolf = st.nextToken().charAt(0) == 'W';
			int cnt = Integer.parseInt(st.nextToken());
			int parent = Integer.parseInt(st.nextToken());
			
			islands[parent].add(i);
			
			if(wolf) cnt *= -1;
			arr[i] = cnt;
		}
		
		find(1, 0);
		
		System.out.println(arr[1]);
		br.close();
	}
	
	private static void find(int idx, int parent) {
		for(int next : islands[idx]) find(next, idx);
		
		if(parent != 0 && arr[idx] > 0) arr[parent] += arr[idx];
	}
	
}