import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();
		int[] dx1 = {-1, 0, 1, 0};
		int[] dy1 = {0, 1, 0, -1};
		for(int i=0; i < K; i++) {
			st = new StringTokenizer(sc.nextLine(), " ");
			int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
			set.add(x + " " + y);
			for(int d=0; d < 4; d++) {
				int nx = x + (dx1[d] * 2);
				int ny = y + (dy1[d] * 2);
				if(nx > 0 && nx <= N && ny > 0 && ny <= N) set.add(nx + " " + ny);
			}
		};
		System.out.println(set.size() - K);
		sc.close();
	}
}