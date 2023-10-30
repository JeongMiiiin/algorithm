import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<String, Boolean> map = new HashMap<>();
		StringTokenizer st;
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String member = st.nextToken();
			if(map.get(member) != null) map.put(member, !map.get(member));
			else map.put(member, true);
		}
		
		List<String> list = new ArrayList<>();
		for(Map.Entry<String, Boolean> entry : map.entrySet()) if(entry.getValue()) list.add(entry.getKey());
		
		Collections.sort(list, Collections.reverseOrder());
		
		for(int i=0; i < list.size(); i++) System.out.println(list.get(i));
		
		br.close();
	}
}