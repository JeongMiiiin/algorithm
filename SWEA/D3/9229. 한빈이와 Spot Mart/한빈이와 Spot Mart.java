import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * SWEA 9229 - 한빈이와 Spot Mart
 * N개의 과자봉지와 ai그램의 무게를 가짐
 * M그램 초과하지 않는 최댓값
*/
public class Solution {
	static int N, M, ans;
	static int R = 2;
	static int[] inputs, numbers;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	
	public static void main(String[] args) throws Exception{
		int T = Integer.parseInt(br.readLine());
		

		for(int t=1; t <= T; t++) {
			//입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			ans = -1;
			inputs = new int[N];
			numbers = new int[R];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i < N; i++)
				inputs[i] = Integer.parseInt(st.nextToken());
			
			comb(0, 0);
			
			//출력
			System.out.println("#" + t + " " + ans);
		}
	}


	private static void comb(int cnt, int start) {
		if(cnt == R) {
			int sum = 0;
			for(int i = 0; i < R; i++)
				sum += numbers[i];
			if(sum > 0 && sum <= M) ans = Math.max(ans, sum);
			return;
		}
		
		for(int i= start; i < N; i++) {
			numbers[cnt] = inputs[i];
			comb(cnt + 1, i + 1);
		}
	}
}
