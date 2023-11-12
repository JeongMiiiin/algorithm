import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numList = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int end = 0;
		for(int i=0; i < N; i++) {
			numList[i] = Integer.parseInt(st.nextToken());
			if(end < numList[i]) end = numList[i];
		}
		int start = 0;
		int total = Integer.parseInt(br.readLine());
		int mid = (start + end) / 2;
		while(start < end) {
			long temp = check(mid, numList, total);
			if(temp < 0) {
				end = mid - 1;
				mid = (start + end) / 2;
			} else {
				start = mid + 1;
				mid = (start + end) / 2;
			}
		}
		if(check(start, numList, total) < 0) start--;
		System.out.println(start);
		br.close();
	}
	
	private static long check(int val, int[] numList, int total) {
		long cnt = 0;
		for(int i=0; i < numList.length; i++) {
			if(numList[i] <= val) cnt += numList[i];
			else cnt += val;
		}
		return total - cnt;
	}
}