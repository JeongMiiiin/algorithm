import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			int[][] materials = new int[N][2];
			for(int i=0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				materials[i][0] = Integer.parseInt(st.nextToken()); //점수 담기
				materials[i][1] = Integer.parseInt(st.nextToken()); //칼로리 담기
			}
			
			int maxScore = 0;
			
			for(int i=1; i < (1 << N); i++) {
				int score = 0;
				int calorie = 0;
				for(int j=0; j < N; j++) {
					if((i & (1 << j)) != 0) {
						score += materials[j][0];
						calorie += materials[j][1];
					}
				}
				if(calorie <= L) maxScore = Math.max(score, maxScore);
			}
			
			//출력
			System.out.println("#" + t + " " + maxScore);
		}
	}
}
