import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i < N; i++) {
			sb.setLength(0);
			for(int j = 0; j < N - i - 1; j++)
				sb.append(" ");
			
			for(int j = 0; j < i + 1; j++)
				sb.append("*");
			
			System.out.println(sb.toString());
		}
			
	}
}