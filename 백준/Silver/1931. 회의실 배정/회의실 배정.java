import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * 백준 1931 - 회의실 배정
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception{
		int N = Integer.parseInt(br.readLine());
		
		int[][] time = new int[N][2];
		
		StringTokenizer st;
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i][0] = Integer.parseInt(st.nextToken());
			time[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(time, new Comparator<int[]>() {
			
			@Override
			public int compare(int[] o1,int[] o2) {
				if(o1[1] == o2[1]) return o1[0] - o2[0];
				else return o1[1] - o2[1];
			}
		});		
		
		int cnt = 0;
		int prevTime = 0;
		for(int i=0; i < N; i++)
			if(prevTime <= time[i][0]) {
				prevTime = time[i][1];
				cnt++;
			}
		
		System.out.println(cnt);
		
	}
}
