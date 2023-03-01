import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//순열
public class Main {
	static int N, R;
	static int[] numbers, inputs;
	static boolean[] visited;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	
	public static void main(String[] args) throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		inputs = new int[N];
		visited = new boolean[N];
		numbers = new int[R];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i < N; i++)
			inputs[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(inputs);
		
		perm(0);
		
		bw.close();
	}


	private static void perm(int cnt) throws IOException {
		if(cnt == R) {
			StringBuilder sb = new StringBuilder();
			for(int n : numbers)
				sb.append(n + " ");
			bw.write(sb + "\n");
			return;
		}
		
		for(int i=0; i < N; i++) {
			if(visited[i]) continue;
			
			numbers[cnt] = inputs[i];
			visited[i] = true;
			perm(cnt + 1);
			visited[i] = false;
		}
		
	}
}
