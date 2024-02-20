import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			map.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		int R = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < R; i++) {
			StringBuilder temp = new StringBuilder();
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			int symptoms = Integer.parseInt(st.nextToken());
			boolean flag = true;
			for(int j=0; j < symptoms; j++) {
				int symptom = Integer.parseInt(st.nextToken());
				if(map.get(symptom) == null) {
					flag = false;
					break;
				} else temp.append(map.get(symptom) + " ");
			}
			if(!flag) sb.append("YOU DIED\n");
			else sb.append(temp.toString() + "\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}