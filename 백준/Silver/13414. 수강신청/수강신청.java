import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();
		for(int i=0; i < L; i++) {
			String s = br.readLine();
			if(map.get(s) != null) map.remove(s);
			map.put(s, true);
		}
		
		StringBuilder sb = new StringBuilder();
		int idx = 0;
		for(Map.Entry<String, Boolean> entry : map.entrySet()) {
			if(idx++ == K) break;
			sb.append(entry.getKey() + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}