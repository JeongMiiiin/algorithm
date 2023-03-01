import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st; 
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = 3;
		
		int[] inputs = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i < N; i++)
			inputs[i] = Integer.parseInt(st.nextToken());
		
		int result = 0;
		
		for(int i=0; i < N - 2; i++)
			for(int j=i+1; j < N - 1; j++)
				for(int z=j+1; z < N; z++) 
					if(result <= inputs[i] + inputs[j] + inputs[z] && inputs[i] + inputs[j] + inputs[z] <= L) result = inputs[i] + inputs[j] + inputs[z];
		
		System.out.println(result);
	}
}