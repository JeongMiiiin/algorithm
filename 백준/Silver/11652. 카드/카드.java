import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Map<Long, Integer> map = new HashMap<>();
		for(int i=0; i < N; i++) {
			long target = sc.nextLong();
			map.put(target, map.getOrDefault(target, 0) + 1);
		}
		long result = 0;
		int cnt = 0;
		for(Entry<Long, Integer> entry : map.entrySet()) {
			if(entry.getValue() > cnt) { //최대값 갱신해야할 때
				cnt = entry.getValue();
				result = entry.getKey();
			} else if(entry.getValue() == cnt) result = Math.min(result, entry.getKey()); //값이 동일할 때
		}
		
		System.out.println(result);
		sc.close();
	}
}