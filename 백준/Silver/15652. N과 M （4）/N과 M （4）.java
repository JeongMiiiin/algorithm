import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//중복조합
public class Main {
	static int N, R;
	static int[] numbers, inputs;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		numbers = new int[R];
		inputs = new int[N];
		
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
			comb(cnt + 1, i);
		}
		
	}
}
