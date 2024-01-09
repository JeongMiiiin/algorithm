import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Map<String, Boolean> map = new HashMap<>();
		for(int i=0; i < N; i++) {
			String s = br.readLine();
			if(map.get(s) == null) map.put(s, true);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < M; i++) {
			String[] arr = br.readLine().split(",");
			for(String s : arr) if(map.get(s) != null) map.remove(s);
				
			sb.append(map.size() + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}