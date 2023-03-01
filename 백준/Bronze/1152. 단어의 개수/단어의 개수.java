import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine().trim();
		if( !st.equals("") ) {
			String[] st1 = st.split(" ");
			System.out.println(st1.length);
		}else {
			System.out.println(0);	
		}
	}
 
}