import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//중복조합
public class Main {
	static int N, R;
	static int[] numbers, inputs;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		inputs = new int[N];
		numbers = new int[R];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i< N; i++)
			inputs[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(inputs);
		
		comb(0,0);
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
		
		for(int i = start; i < N; i++) {
			numbers[cnt] = inputs[i];
			comb(cnt + 1, i);
		}
		
	}
}
