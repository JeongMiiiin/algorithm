import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N + M == 0) break;
			List<Integer> A = new ArrayList<>();
			List<Integer> B = new ArrayList<>();
			for(int i=0; i < N; i++) A.add(Integer.parseInt(br.readLine()));
			for(int i=0; i < M; i++) B.add(Integer.parseInt(br.readLine()));
			int result = 0;
			for(int i=0; i < N; i++) {
				int cur = A.get(i);
				int left = 0;
				int right = M - 1;
				if(B.get(left) > cur || B.get(right) < cur) continue;
				if(B.get(left) == cur || B.get(right) == cur) {
					result++;
					continue;
				}
				while(left < right) {
					int mid = (left + right) / 2;
					if(cur < B.get(mid)) right = mid;
					else if(cur > B.get(mid)) left = mid + 1;
					else {
						left = mid;
						break;
					}
				}
				if(B.get(left) == cur) result++;
			}
			
			System.out.println(result);
		}
		
		br.close();
	}
}