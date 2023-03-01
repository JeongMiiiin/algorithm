import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, R;
	static int[] numbers, inputs;
	
	public static void main(String[] args) throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		inputs = new int[N];
		numbers = new int[R];
		
		for(int i=1; i <= N; i++)
			inputs[i - 1] = i;
		
		comb(0, 0);
		
		bw.close();
	}

	private static void comb(int cnt, int start) throws IOException {
		if(cnt == R) {
			StringBuilder sb = new StringBuilder();
			for(int n : numbers)
				sb.append(n + " ");
			bw.write(sb + "\n");
			return;
		}
		
		for(int i=start; i < N; i++) {
			numbers[cnt] = inputs[i];
			comb(cnt + 1, i + 1);
		}
		
	}
}
