import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 2805번 나무자르기
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] woods = new int[N];
		st = new StringTokenizer(br.readLine());
		long max = Integer.MIN_VALUE;
		for(int i=0; i < N; i++) {
			woods[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, woods[i]);
		}
		long min = 0;
		long mid = 0;
		
		while(min < max) {
			mid = (max + min) / 2;
			
			long cnt = 0;
			for(int i=0; i < woods.length; i++)
				cnt += woods[i] - mid >= 0 ? woods[i] - mid : 0;
			
			if(cnt < M) max = mid;
			else min = mid + 1;
		}
		System.out.println(min - 1);
	}
}
