import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 1780번 - 종이의 개수
*/
public class Main {
	static int minusCnt, zeroCnt, plusCnt;
	static int[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		division(0,0,N);
		System.out.println(minusCnt);
		System.out.println(zeroCnt);
		System.out.println(plusCnt);
	}
	
	private static void division(int r, int c, int size) {
		int[] fre = new int[3];
		for(int i=r; i < r + size; i++)
			for(int j=c; j < c + size; j++) fre[map[i][j] + 1]++;
		
		if(fre[0] == size * size) { //면이 전부 -1인 경우
			minusCnt++;
		} else if(fre[1] == size * size) { //면이 전부 0인 경우
			zeroCnt++;
		} else if(fre[2] == size * size) { //면이 전부 1인 경우
			plusCnt++;
		} else { //분할해야하는 경우 - 9분할
			int divisionSize = size / 3;
			division(r, c, divisionSize); //1분면
			division(r, c + divisionSize, divisionSize); //2분면
			division(r, c + divisionSize * 2, divisionSize); //3분면
			division(r + divisionSize, c, divisionSize); //4분면
			division(r + divisionSize, c + divisionSize, divisionSize); //5분면
			division(r + divisionSize, c + divisionSize * 2, divisionSize); //6분면
			division(r + divisionSize * 2, c, divisionSize); //7분면
			division(r + divisionSize * 2, c + divisionSize, divisionSize); //8분면
			division(r + divisionSize * 2, c + divisionSize * 2, divisionSize); //9분면
		}
		
	}
}
