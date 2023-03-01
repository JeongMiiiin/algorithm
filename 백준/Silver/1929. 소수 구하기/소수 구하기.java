import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//소수 구하기
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		boolean[] isPrime = new boolean[N + 1];
		
		isPrime[0] = true;
		isPrime[1] = true;
		
		for(int i = 2; i <= Math.sqrt(N); i++) {
			if(isPrime[i]) continue;
			
			for(int j = i * i; j < N + 1; j = j + i)
				isPrime[j] = true;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=M; i <= N; i++)
			if( !isPrime[i] ) sb.append(i + "\n");
		
		System.out.print(sb.toString());
		
	}
}
