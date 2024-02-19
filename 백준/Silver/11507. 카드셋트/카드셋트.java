import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, Set<Integer>> map = new HashMap<>();
		map.put("P", new HashSet<>());
		map.put("K", new HashSet<>());
		map.put("H", new HashSet<>());
		map.put("T", new HashSet<>());
		boolean duplicate = false;
		String s = sc.nextLine();
		for(int i=0; i < s.length(); i += 3) {
			String target = s.substring(i, i + 1);
			int num = Integer.parseInt(s.substring(i + 1, i + 3));
			if(map.get(target).contains(num)) {
				duplicate = true;
				break;
			} else map.get(target).add(num);
		}
		if(!duplicate) {
			StringBuilder sb = new StringBuilder();
			sb.append(13 - map.get("P").size() + " ");
			sb.append(13 - map.get("K").size() + " ");
			sb.append(13 - map.get("H").size() + " ");
			sb.append(13 - map.get("T").size());
			System.out.println(sb.toString());
		} else System.out.println("GRESKA");
		sc.close();
	}
}