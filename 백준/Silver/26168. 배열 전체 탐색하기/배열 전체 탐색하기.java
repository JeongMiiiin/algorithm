import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] arr = new long[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < N; i++) arr[i] = Long.parseLong(st.nextToken());
		Arrays.sort(arr);
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int status = Integer.parseInt(st.nextToken());
			long target = Long.parseLong(st.nextToken());
			if(status == 1) { //크거나 같은 원소 개수 찾기
				int left = 0, right = N - 1;
				while(left < right) {
					int mid = (left + right) / 2;
					if(arr[mid] >= target) right = mid;
					else left = mid + 1;
				}
				while(left >= 0 && arr[left] >= target) left--;
				
				bw.write(N - (left + 1) + "\n");
			} else if(status == 2) {
				int left = 0, right = N - 1;
				while(left < right) {
					int mid = (left + right) / 2;
					if(arr[mid] > target) right = mid;
					else left = mid + 1;
				}
				while(left >= 0 && arr[left] > target) left--;
				
				bw.write(N - (left + 1) + "\n");
			} else {
				int left = 0, right = N - 1;
				while(left < right) {
					int mid = (left + right) / 2;
					if(arr[mid] >= target) right = mid;
					else left = mid + 1;
				}
				while(left >= 0 && arr[left] >= target) left--;
				int start = left + 1;
				long target2 = Long.parseLong(st.nextToken());
				left = 0;
				right = N - 1;
				while(left < right) {
					int mid = (left + right) / 2;
					if(arr[mid] >= target2) right = mid;
					else left = mid + 1;
				}
				while(left < N && arr[left] <= target2) left++;
				bw.write(left - start + "\n");
			}
		}
		
		br.close();
		bw.close();
	}
}