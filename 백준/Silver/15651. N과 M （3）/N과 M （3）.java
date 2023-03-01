import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	private static int N, M;
	private static int[] numbers;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
		
		numbers = new int[M];
		
		perm(0);
		bw.close();
		
	}

	private static void perm(int cnt) throws IOException {
		//기저조건
		if(cnt == M) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i < M; i++) {
				sb.append(numbers[i] + " ");
			}
			bw.write(sb.toString() + "\n");
			return;
		}
				
		for(int i=0; i < N; i++) {
			numbers[cnt] = i + 1;
			perm(cnt + 1);
		}
	}
}