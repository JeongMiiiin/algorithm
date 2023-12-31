import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int result = 0;
		int[] cntArr = new int[31];
		
		if(N > 0) {
			for(int i=0; i < N; i++) cntArr[Integer.parseInt(br.readLine())]++;
			int exceptCnt = (int) Math.round(N * 0.15);
			
			double total = 0;
			int minTemp = exceptCnt, maxTemp = exceptCnt;
			
			int startIdx = 1, temp = 0;
			while(minTemp > 0) {
				if(cntArr[startIdx] > 0) {
					temp = cntArr[startIdx];
					if(cntArr[startIdx] >= minTemp) cntArr[startIdx] -= minTemp;
					else cntArr[startIdx] = 0;
					minTemp -= temp;
				} 
				startIdx++;
			}
			startIdx = 30;
			while(maxTemp > 0) {
				if(cntArr[startIdx] > 0) {
					temp = cntArr[startIdx];
					if(cntArr[startIdx] >= maxTemp) cntArr[startIdx] -= maxTemp;
					else cntArr[startIdx] = 0;
					maxTemp -= temp;
				}
				startIdx--;
			}
			
			for(int i=1; i <= 30; i++) total += cntArr[i] * i;
			
			result = (int) Math.round(total / (N - exceptCnt * 2));
		}
		
		System.out.println(result);
		br.close();
	}
}