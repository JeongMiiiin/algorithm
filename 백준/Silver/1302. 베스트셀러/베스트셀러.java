import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, Integer> map = new HashMap<>();
		int N = Integer.parseInt(sc.nextLine());
		int max = 0;
		for(int i=0; i < N; i++) {
			String s = sc.nextLine();
			map.put(s, map.getOrDefault(s, 0) + 1);
			max = Math.max(max, map.get(s));
		}
		List<String> list = new ArrayList<>();
		for(Map.Entry<String, Integer> entry : map.entrySet()) if(entry.getValue() == max) list.add(entry.getKey());
		Collections.sort(list);
		
		System.out.println(list.get(0));
		sc.close();
	}
}