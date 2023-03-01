import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 백준 1992번 쿼드트리
*/
public class Main {
	static int N;
	static int[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		char[] text;
		for(int i=0; i < N; i++) {
			text = br.readLine().toCharArray();
			for(int j=0; j < N; j++) if(text[j] != '0') map[i][j] = 1;
		}
		
		division(0,0,N);
		
		System.out.println(sb.toString());
	}

	private static void division(int r, int c, int size) {
		int sum = 0;
		for(int i=r; i < r + size; i++)
			for(int j=c; j < c + size; j++) sum += map[i][j];
		
		//모두 다 1일 때
		if(sum == size * size) sb.append("1");
		//모두 다 0일 때
		else if(sum == 0) sb.append("0");
		else {
			int half = size / 2;
			sb.append("(");
			division(r, c, half); //1사분면
			division(r, c + half, half);  //2사분면
			division(r + half, c, half); //3사분면
			division(r + half, c + half, half); //4사분면
			sb.append(")");
		}
	}
}
