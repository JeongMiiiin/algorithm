import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Map<Integer, Integer> map = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			int command = Integer.parseInt(st.nextToken());
			if(command == 1) {
				int num = Integer.parseInt(st.nextToken());
				map.put(Integer.parseInt(st.nextToken()), num);
			} else sb.append(map.get(Integer.parseInt(st.nextToken())) + "\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
}