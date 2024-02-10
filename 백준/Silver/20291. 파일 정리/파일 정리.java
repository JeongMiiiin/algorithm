import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		TreeMap<String, Integer> treemap = new TreeMap<>();
		for(int i=0; i < N; i++) {
			String[] s = sc.nextLine().split("\\.");
			if(treemap.get(s[1]) != null) treemap.put(s[1], treemap.get(s[1]) + 1);
			else treemap.put(s[1], 1);
		}
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, Integer> entry : treemap.entrySet()) sb.append(entry.getKey() + " " + entry.getValue() + "\n");
		
		System.out.println(sb.toString());
		sc.close();
	}
}