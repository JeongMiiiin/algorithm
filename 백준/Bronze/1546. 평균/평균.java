import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] numbers = new int[N];
		
		double max = 0;
		for(int i=0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			numbers[i] = n;
			max = Math.max(max, n);
		}
		
		double sum = 0;
		for(int n : numbers)
			sum += (n / max) * 100;
		
		System.out.println(sum / N);
	}
}
