import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 백준 5525 - IOIOI
 * 
 * N + 1개의 I와 N개의 O로 이루어져 교대로 나오는 문자열을 P(N)이라고 한다.
 * I와 0로만 이루어진 문자열 S와 정수 N이 주어졌을 때, S안에 P(N)이 몇 군데 포함되어 있는지 구하는 프로그램 작성
*/
public class Main {
	static int N, M, ans = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		char[] s = br.readLine().toCharArray();
		
		for(int i=0; i < M - (N * 2); i++) if(s[i] == 'I') find(s, i);
		
		System.out.println(ans);
	}
	private static void find(char[] s, int start) {
		boolean flag = true;
		for(int i=1; i <= N * 2; i++) {
			if(i % 2 == 0 && s[start + i] == 'I') continue;
			else if(i % 2 == 1 && s[start + i] == 'O') continue;
			return;
		}
		if(flag) ans++;
		
	}
}