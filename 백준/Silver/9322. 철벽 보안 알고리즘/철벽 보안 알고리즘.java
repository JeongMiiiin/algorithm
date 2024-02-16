import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t <= T; t++) {
			int N = Integer.parseInt(sc.nextLine());
			Map<String, Integer> map = new HashMap<>();
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			for(int i=0; i < N; i++) map.put(st.nextToken(), i);
			st = new StringTokenizer(sc.nextLine(), " ");
			int[] arr = new int[N];
			for(int i=0; i < N; i++) arr[i] = map.get(st.nextToken());
			String[] info = new String[N];
			st = new StringTokenizer(sc.nextLine(), " ");
			for(int i=0; i < N; i++) info[arr[i]] = st.nextToken();
			StringBuilder temp = new StringBuilder();
			for(int i=0; i < N; i++) temp.append(info[i] + " ");
			temp.append("\n");
			sb.append(temp.toString());
		}
		System.out.println(sb.toString());
		sc.close();
	}
}