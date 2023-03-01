import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//백준 2961번 도영이가 만든 맛있는 음식
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception{
		int N = Integer.parseInt(br.readLine());
		int[][] materials = new int[N][2];
		
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			materials[i][0] = Integer.parseInt(st.nextToken());
			materials[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int min = Integer.MAX_VALUE;
		for(int i=1; i < (1 << N); i++) {
			int temp1 = 1;
			int temp2 = 0;
			for(int j=0; j < N; j++) {
				if((i & (1 << j)) == 0) continue;
				
				temp1 *= materials[j][0];
				temp2 += materials[j][1];
			}
			int temp = Math.abs(temp1 - temp2);
			min = Math.min(min, temp);
		}
		System.out.println(min);
			
	}
}
