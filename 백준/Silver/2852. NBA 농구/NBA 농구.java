import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] score = new int[3];
		int[] timeScore = new int[3];
		int prevTime = 0, prevIdx = 0;
		for(int i=0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			String[] timeInfo = st.nextToken().split(":");
			score[num]++;
			int curTime = Integer.parseInt(timeInfo[0]) * 60 + Integer.parseInt(timeInfo[1]);
			if(score[1] != score[2]) {
				if(score[prevIdx] < score[num]) {
					prevIdx = num;
					prevTime = curTime;
				}
			} else {
				timeScore[prevIdx] += curTime - prevTime; //더해주기
				prevIdx = 0;
			}
		}
		
		if(prevIdx != 0) timeScore[prevIdx] += (48 * 60 - prevTime);
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i <= 2; i++) {
			String min = timeScore[i] / 60 < 10 ? "0" + Integer.toString(timeScore[i] / 60) : Integer.toString(timeScore[i] / 60);
			String sec = timeScore[i] % 60 < 10 ? "0" + Integer.toString(timeScore[i] % 60) : Integer.toString(timeScore[i] % 60);
			sb.append(min + ":" + sec + "\n");
		} 
		
		System.out.println(sb.toString());
		br.close();
	}
}