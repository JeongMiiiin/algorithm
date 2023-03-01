import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 16935번 - 배열 돌리기 3
 * 주어지는 값
 * N : 배열의 가로크기
 * M : 배열의 세로크기
 * R : 연산의 수
 * 둘째줄부터 N개의 줄 : 배열의 원소
 * 마지막 줄에는 수행해야하는 연산의 종류가 주어짐
 * 연산의 종류
 * 1. 배열을 상하반전
 * 2. 배열을 좌우반전
 * 3. 배열을 오른쪽으로 90도 회전
 * 4. 배열을 왼쪽으로 90도 회전
 * 5. 배열을 4개의 부분배열로 만들고, 시계방향으로 회전
 * 6. 배열을 4개의 부분배열로 만들고, 반시계방향으로 회전
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, M;
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		//입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken()); //연산횟수
		map = new int[N][M];
		
		//배열 만들기
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i < R; i++)
			initCmd(Integer.parseInt(st.nextToken()));
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N; i++) {
			for(int j=0; j < M; j++)
				sb.append(map[i][j] + " ");
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}

	//연산의 종류를 구분하여 실행하는 함수
	private static void initCmd(int cmd) {
		switch(cmd) {
		case 1 :
			upDownReverse();
			break;
		case 2 :
			leftRightReverse();
			break;
		case 3 :
			rightTurn();
			break;
		case 4 :
			leftTurn();
			break;
		case 5 :
			spliceTurn();
			break;
		case 6 :
			spliceReverseTurn();
			break;
		}
		
	}
	
	//상하반전
	private static void upDownReverse() {
		for(int i=0; i < N / 2; i++) {
			int[] prev = map[i];
			map[i] = map[N - i - 1];
			map[N - i - 1] = prev;
		}
	}
	
	//좌우반전
	private static void leftRightReverse() {
		for(int i=0; i < M / 2; i++) {
			for(int j=0; j < N; j++) {
				int prev = map[j][i];
				map[j][i] = map[j][M - i - 1];
				map[j][M - i - 1] = prev;
			}
		}
	}
	
	//오른쪽90도회전
	private static void rightTurn() {
		int[][] newMap = new int[M][N];
		int c = N - 1;
		for(int i=0; i < N; i++) {
			for(int j=0; j< M; j++) newMap[j][c] = map[i][j];
			c--;
		}
		int temp = N;
		N = M;
		M = temp;
		
		map = newMap;
	}
	
	//왼쪽90도회전
	private static void leftTurn() {
		int[][] newMap = new int[M][N];
		
		for(int i=0; i < N; i++) {
			for(int j=0; j< M; j++) newMap[M - j - 1][i] = map[i][j];
		}
		int temp = N;
		N = M;
		M = temp;
		
		map = newMap;
	}
	
	private static void spliceTurn() {
		int[][] newMap = new int[N][M];
		
		int rmid = N / 2;
		int cmid = M / 2;
		
		for(int i=0; i < rmid; i++)
			for(int j=0; j < cmid; j++) newMap[i][cmid + j] = map[i][j];
		
		for(int i=0; i < rmid; i++)
			for(int j= cmid; j < M; j++) newMap[rmid + i][j] = map[i][j];
		
		for(int i=rmid; i < N; i++)
			for(int j = cmid, c = 0; j < M; j++, c++) newMap[i][c] = map[i][j];
		
		for(int i=rmid, r=0; i < N; i++, r++)
			for(int j = 0; j < cmid; j++) newMap[r][j] = map[i][j];
		
		map = newMap;
	}
	
	private static void spliceReverseTurn() {
		int[][] newMap = new int[N][M];
		
		int rmid = N / 2;
		int cmid = M / 2;
		
		for(int i=0; i < rmid; i++)
			for(int j=0; j < cmid; j++) newMap[rmid + i][j] = map[i][j];
		
		for(int i=rmid; i < N; i++)
			for(int j= 0; j < cmid; j++) newMap[i][cmid + j] = map[i][j];
		
		for(int i=rmid, r=0; i < N; i++, r++)
			for(int j = cmid; j < M; j++) newMap[r][j] = map[i][j];
		
		for(int i=0; i < rmid; i++)
			for(int j = cmid, c=0; j < M; j++, c++) newMap[i][c] = map[i][j];
		
		map = newMap;
	}
}
