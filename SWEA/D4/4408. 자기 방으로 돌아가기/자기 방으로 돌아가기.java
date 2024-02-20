import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int result = 1;
			int[] info = new int[201];
			for(int i=0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				if(start > end) {
					int temp = start;
					start = end;
					end = temp;
				}
				for(int j=start; j <= end; j++) {
					if(j % 2 == 0) result = Math.max(result, ++info[j / 2]);
					else {
						result = Math.max(result, ++info[(j / 2) + 1]);
						j++;
					}
				}
			}
			bw.write("#" + t + " " + result + "\n");
		}
		bw.close();
		br.close();
	}
}