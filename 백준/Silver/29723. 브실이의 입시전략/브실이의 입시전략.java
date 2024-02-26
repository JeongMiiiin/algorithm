import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Map<String, Integer> map = new HashMap<>();
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(sc.nextLine());
			map.put(st.nextToken(), Integer.parseInt(st.nextToken()));
		}
		int essential = 0;
		for(int i=0; i < K; i++) {
			String s = sc.nextLine();
			essential += map.get(s);
			map.remove(s);
		}
		List<Integer> minList = new ArrayList<>();
		List<Integer> maxList = new ArrayList<>();
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			minList.add(entry.getValue());
			maxList.add(entry.getValue());
		}
		Collections.sort(minList);
		Collections.sort(maxList, Collections.reverseOrder());
		
		int min = essential, max = essential;
		for(int i=0; i < M - K; i++) {
			min += minList.get(i);
			max += maxList.get(i);
		}
			
		System.out.println(min + " " + max);
		sc.close();
	}
}