import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

// 백준 10989번 수 정렬하기 3
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		int[] NList = new int[N];
		for(int i=0; i < N; i++)
			NList[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(NList);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N; i++)
			sb.append(NList[i] + "\n");
		
		bw.write(sb.toString() + "\n");
		bw.close();
	}
}
