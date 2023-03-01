import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 2630 - 색종이 만들기
*/
public class Main {
	static int blueCnt = 0, whiteCnt = 0, N;
	static int[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		StringTokenizer st;
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		partition(0,0,N);
		
		System.out.println(whiteCnt);
		System.out.println(blueCnt);
	}


	public static void partition(int row, int col, int size) {
		
		if(colorCheck(row, col, size)) {
			if(map[row][col] == 0) {
				whiteCnt++;
			}
			else {
				blueCnt++;
			}
			return;
		}
		
		int newSize = size / 2;	// 절반 사이즈
		// 재귀 호출
		partition(row, col, newSize);						// 2사분면
		partition(row, col + newSize, newSize);				// 1사분면
		partition(row + newSize, col, newSize);				// 3사분면
		partition(row + newSize, col + newSize, newSize);	// 4사분면
	}
	
	// 현재 파티션의 컬러가 같은지 체크한다.
	public static boolean colorCheck(int row, int col, int size) {
		
		int color = map[row][col];	// 첫 번째 원소를 기준으로 검사
			
		for(int i = row; i < row + size; i++) {
			for(int j = col; j < col + size; j++) { 
				if(map[i][j] != color) {
					return false;
				}
			}
		}
		// 검사가 모두 통과했다는 의미이므로 true 리턴
		return true;
	}
}
