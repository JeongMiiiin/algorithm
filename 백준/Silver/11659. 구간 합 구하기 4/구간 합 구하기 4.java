import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	/*
	 * BOJ 11659
	 * 주어지는 값
	 * 첫째줄 -> N : 수의 개수, M : 합을 구해야 하는 횟수
	 * 둘째줄 -> N개의 수
	 * M개의 줄 -> 합을 구해야 하는 구간 i j
	*/
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine()); 
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] inputs = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		inputs[1] = Integer.parseInt(st.nextToken());
		for(int i=2; i <= N; i++)
			inputs[i] = inputs[i - 1] + Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int result = inputs[end] - inputs[start - 1];
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
				
	}
}
