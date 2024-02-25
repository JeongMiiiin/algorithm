import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Map<String, Integer> map = new HashMap<>();
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		for(int i=0; i < N; i++) map.put(st.nextToken(), i);
		st = new StringTokenizer(sc.nextLine(), " ");
		String[] info = new String[N];
		for(int i=0; i < N; i++) info[i] = st.nextToken();
		int result = 0;
		for(int i=0; i < N; i++) {
			int idx = map.get(info[i]);
			for(int j= i + 1; j < N; j++) if(idx < map.get(info[j])) result++;
		}
		
		System.out.println(result + "/" + (N * (N - 1) / 2));
		sc.close();
	}
}