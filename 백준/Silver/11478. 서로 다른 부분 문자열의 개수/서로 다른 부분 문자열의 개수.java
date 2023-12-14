import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		Map<String, Boolean> map = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		int result = 0;
		for(int i=0; i < s.length(); i++) {
			sb.setLength(0);
			sb.append(s.charAt(i));
			if(map.get(sb.toString()) == null) {
				map.put(sb.toString(), true);
				result++;
			}
			for(int j= i + 1; j < s.length(); j++) {
				sb.append(s.charAt(j));
				if(map.get(sb.toString()) == null) {
					map.put(sb.toString(), true);
					result++;
				}
			}
		}
		
		System.out.println(result);
		br.close();
	}
}