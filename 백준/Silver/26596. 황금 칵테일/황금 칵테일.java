import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Map<String, Double> map = new HashMap<>();
		for(int i=0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			String s = st.nextToken();
			map.put(s, map.getOrDefault(s, 0.0) + Double.parseDouble(st.nextToken()));
		}
		boolean result = false;
		outer : for(Map.Entry<String, Double> entry : map.entrySet()) {
			Double shake = Math.floor(entry.getValue() * 1.618);
			for(Map.Entry<String, Double> test : map.entrySet()) {
				if(entry.getKey().equals(test.getKey())) continue; //비교군이 같으면 패스
				if(shake == Math.floor(test.getValue())) {
					result = true;
					break outer;
				}
			}
		}
		
		System.out.println(result ? "Delicious!" : "Not Delicious...");
		sc.close();
	}
}