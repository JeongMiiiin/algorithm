import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedHashMap<String, Double> map = new LinkedHashMap<>();
		map.put("Re", 0.0);
		map.put("Pt", 0.0);
		map.put("Cc", 0.0);
		map.put("Ea", 0.0);
		map.put("Tb", 0.0);
		map.put("Cm", 0.0);
		map.put("Ex", 0.0);
		Double total = 0.0;
		for(int i=0; i < 24; i++) {
			if(sc.hasNext()) {
				total++;
				String s = sc.next();
				if(map.get(s) == null) continue;
				map.put(s, map.get(s) + 1);
			} else break;
		}
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, Double> entry : map.entrySet()) {
			sb.append(entry.getKey() + " " + ((int) Math.floor(entry.getValue())) + " " + String.format("%.2f", entry.getValue() / total) + "\n");
		}
		sb.append("Total " + ((int) Math.floor(total)) + " 1.00");
		System.out.println(sb.toString());
		sc.close();
	}
}