import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Map<String, Integer> map = new HashMap<>();
		int result = 0;
		for(int i=0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			String employee = st.nextToken();
			String status = st.nextToken();
			if(status.equals("+")) map.put(employee, map.getOrDefault(employee, 0) + 1);
			else {
				if(map.get(employee) != null && map.get(employee) > 0) map.put(employee, map.get(employee) - 1);
				else result++;
			}
		}
		for(Map.Entry<String, Integer> entry : map.entrySet()) result += entry.getValue();
		
		System.out.println(result);
		sc.close();
	}
}