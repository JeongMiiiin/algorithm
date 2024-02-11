import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int Ti = Integer.parseInt(st.nextToken());
			boolean flag = false;
			Map<Long, Integer> map = new HashMap<>();
			for(int j=0; j < Ti; j++) {
				long num = Long.parseLong(st.nextToken());
				map.put(num, map.getOrDefault(num, 0) + 1);
				if(map.get(num) > Ti / 2) {
					sb.append(num + "\n");
					flag = true;
					break;
				}
			}
			if(!flag) sb.append("SYJKGW\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}