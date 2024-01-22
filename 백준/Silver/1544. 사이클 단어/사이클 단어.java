import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<Integer, Set<String>> map = new HashMap<>();
		int N = Integer.parseInt(sc.nextLine());
		int result = 0;
		for(int i=0; i < N; i++) {
			String s = sc.nextLine();
			if(map.get(s.length()) != null) {
				StringBuilder sb = new StringBuilder(s);
				int idx = 0;
				while(idx < s.length() && !map.get(s.length()).contains(sb.toString())) {
					sb.append(sb.charAt(0));
					sb.delete(0, 1);
					idx++;
				}
				if(idx == s.length()) {
					result++;
					map.get(s.length()).add(s);
				}
			} else {
				result++;
				Set<String> temp = new HashSet<>();
				temp.add(s);
				map.put(s.length(), temp);
			}
		}
		System.out.println(result);
		sc.close();
	}
}