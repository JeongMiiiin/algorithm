import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 스네이크 버드
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] fruits = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i < N; i++) fruits[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(fruits);
		
		for(int i=0; i < N; i++)
			if(L >= fruits[i]) L++;
		
		System.out.println(L);
	}
}
