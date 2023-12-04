import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] nList = new int[N];
		int result = 0, max = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < N; i++) {
			nList[i] = Integer.parseInt(st.nextToken());
			max = Math.max(nList[i], max);
		}
		int left = 1, right = max;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			int cnt = 0;
			for(int i=0; i < N; i++) cnt += nList[i] / mid;
			if(cnt >= M) {
				result = mid;
				left = mid + 1;
			} else right = mid - 1;
		}
		
		System.out.println(result);
		br.close();
	}
}