import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception{
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder st = new StringBuilder(br.readLine());
		int sum = 0;
		for(int i=0; i < N; i++)
			sum += Integer.parseInt(st.substring(i,i + 1));
		
		bw.write(sum + "\n");
		bw.close();
	}
}