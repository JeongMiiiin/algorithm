import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception{
		
		while(true) {
			String sb = br.readLine();
			if(sb.equals("0")) break;
			String result = "yes";
			for(int i=0; i < sb.length() / 2; i++) {
				if( !sb.substring(i, i+ 1).equals( sb.substring( sb.length() - i - 1, sb.length() - i ) ) ) {
					result = "no";
					break;
				}
			}
			bw.write(result + "\n");
		}
		
		bw.close();
	}
}