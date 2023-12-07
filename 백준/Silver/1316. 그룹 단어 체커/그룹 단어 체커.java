import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] check = new boolean[26];
		int N = Integer.parseInt(br.readLine());
		int result = 0;
		outer : for(int i=0; i < N; i++) {
			Arrays.fill(check, false);
			char[] targets = br.readLine().toCharArray();
			char c = targets[0], cur;
			check[c - 'a'] = true;
			for(int j=1; j < targets.length; j++) {
				cur = targets[j];
				if(cur != c) {
					if(check[cur - 'a']) continue outer;
					else {
						check[cur - 'a'] = true;
						c = cur;
					}
				}
			}
			result++;
		}
		System.out.println(result);
		br.close();
	}
}