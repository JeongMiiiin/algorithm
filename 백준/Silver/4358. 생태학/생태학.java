import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double cnt = 0;
		TreeMap<String, Double> treemap = new TreeMap<>();
		while(true) {
			String s = br.readLine();
			if(s == null || s.equals("")) break;
			cnt++;
			treemap.put(s, treemap.getOrDefault(s, 0.0) + 1);
		}
		
		StringBuilder sb = new StringBuilder();
		for(Entry<String, Double> entry : treemap.entrySet()) sb.append(entry.getKey() + " " + String.format("%.4f", (entry.getValue() / cnt) * 100) + "\n");
		
		System.out.println(sb.toString());
		br.close();
	}
}