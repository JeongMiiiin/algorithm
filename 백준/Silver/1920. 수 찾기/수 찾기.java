import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] inputs;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		inputs = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i < N; i++)
			inputs[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(inputs);
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < M; i++)
			sb.append( BSearch(Integer.parseInt(st.nextToken())) + "\n");
		
		System.out.println(sb.toString());
	}
	
	public static int BSearch(int n) {
		int left = 0;
		int right = inputs.length - 1;
		int mid;

		while (left <= right) {
			mid = (left + right) / 2;
			if (inputs[mid] < n) left = mid + 1;
			else if (inputs[mid] > n) right = mid - 1;
			else return 1;
		}
		return 0;
	}
	
}
