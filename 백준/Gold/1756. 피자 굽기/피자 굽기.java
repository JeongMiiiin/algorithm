import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int D = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		//오븐 세팅
		int[] oven = new int[D];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < D; i++) oven[i] = Integer.parseInt(st.nextToken());
		for(int i=1; i < D; i++) if(oven[i] > oven[i - 1]) oven[i] = oven[i - 1];
		int result = Integer.MAX_VALUE;
		int top, bottom, temp = D - 1;
		
		//결과 계산
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < N; i++) {
			int pizza = Integer.parseInt(st.nextToken());
			int res = -1;
			top = 0;
			bottom = temp;
			while(top <= bottom) {
				int mid = (top + bottom) / 2;
				if(oven[mid] >= pizza) {
					res = mid;
					top = mid + 1;
				} else bottom = mid - 1;
			}
			
			result = Math.min(result, res);
			temp = res - 1;
		}
		
		System.out.println(++result);
		br.close();
	}
}