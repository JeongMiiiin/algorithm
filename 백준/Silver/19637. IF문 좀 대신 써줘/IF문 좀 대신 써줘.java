import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Title{
		String title;
		int idx, score;
		public Title(String title, int idx, int score) {
			this.title = title;
			this.idx = idx;
			this.score = score;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Title[] arr = new Title[N];
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Title(st.nextToken(), i, Integer.parseInt(st.nextToken()));
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < M; i++) {
			int target = Integer.parseInt(br.readLine());
			int left = 0, right = N - 1;
			while(left < right) {
				int mid = (left + right) / 2;
				if(arr[mid].score >= target) right = mid;
				else left = mid + 1;
			}
			sb.append(arr[left].title + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}