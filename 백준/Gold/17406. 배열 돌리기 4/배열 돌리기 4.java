import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 17406번 배열 돌리기 4
 * 주어지는 값
 * N : 배열의 가로 크기
 * M : 배열의 세로 크기
 * R : 회전연산 횟수
 * 2번째 줄부터 N번째줄까지 : 배열의 원소
 * N+1번째 줄부터 N+1+R번째줄까지 : 회전 연산에 대한 값
 * 회전연산에 대한 값
 * (r,c,s)
 * 가장 왼쪽 윗칸이 (r-s,c-s).
 * 가장 오른쪽 아랫칸이 (r+s,c+s).
 * 해당 크기의 직사각형을 각 줄마다 시계방향으로 1칸씩 이동
 * 연산이 끝나고 배열의 값을 구하라
 * (배열의 값 : 각 행들의 원소 합 중 최솟값)
 * 각 크기를 잡고 회전
*/
public class Main {
	static int N, M, R, min;
	static int[] numbers;
	static int[][] map, testCase;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		//맵에 대한 정보 저장
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		min = Integer.MAX_VALUE;
		
		numbers = new int[R];
		testCase = new int[R][3];
		for(int i=0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			testCase[i][0] = Integer.parseInt(st.nextToken());
			testCase[i][1] = Integer.parseInt(st.nextToken());
			testCase[i][2] = Integer.parseInt(st.nextToken());
			numbers[i] = i;
		}
		
		NPPerm(numbers);
		
		//perm(0, 0);
		
		
		System.out.println(min);
	}
	

	private static void NPPerm(int[] inputs) {
		Arrays.sort(inputs);
		
		do {			
			int[][] newMap = new int[N][M];
			for(int i=0; i < N; i++)
				for(int j=0; j < M; j++) newMap[i][j] = map[i][j];
			
			for(int i=0; i < R; i++) {
				int target = numbers[i];
				int r = testCase[target][0];
				int c = testCase[target][1];
				int s = testCase[target][2];
				rotateMap(newMap, r, c, s);
			}
			for(int i=0; i < N; i++) {
				int sum = 0;
				for(int j=0; j < M; j++) sum += newMap[i][j];
				
				min = Math.min(min, sum);
			}
			
		} while(np(inputs));
		
	}


	private static boolean np(int[] inputs) {
		int n = inputs.length;
		
		//step 1. 꼭대기 앞 교환할 값을 찾는다.
		int i = n - 1;
		while(i > 0 && inputs[i-1] > inputs[i]) i--;
		if(i == 0) return false;
		
		//step 2. 교환할 값을 맨 뒤에서부터 찾는다.
		int j = n - 1;
		while(inputs[i - 1] >= inputs[j]) j--;
		
		//step 3. 둘의 값을 교환한다
		swap(inputs, i - 1, j);
		
		//step 4. 꼭대기부터 맨 뒤까지 오름차순으로 정렬
		int k = n - 1;
		while(i < k) swap(inputs, i++, k--);
		
		return true;
		
	}


	private static void swap(int[] inputs, int i, int j) {
		int temp = inputs[i];
		inputs[i] = inputs[j];
		inputs[j] = temp;
		
	}


	private static void perm(int cnt, int flag) {
		if(cnt == R) {
			int[][] newMap = new int[N][M];
			
			for(int i=0; i < N; i++)
				for(int j=0; j < M; j++) newMap[i][j] = map[i][j];
			
			for(int i=0; i < R; i++) {
				int target = numbers[i];
				int r = testCase[target][0];
				int c = testCase[target][1];
				int s = testCase[target][2];
				rotateMap(newMap, r, c, s);
			}
			for(int i=0; i < N; i++) {
				int sum = 0;
				for(int j=0; j < M; j++) sum += newMap[i][j];
				
				min = Math.min(min, sum);
			}
			
			return;
		}
		
		for(int i= 0; i < R; i++) {
			if((flag & (1 << i)) != 0 ) continue;
			
			numbers[cnt] = i;
			perm(cnt + 1, (flag | 1 << i));
		}
	}


	private static void rotateMap(int[][] newMap, int r, int c, int s) {
		int startX = r - s - 1;
		int startY = c - s - 1;
		int endX = r + s - 1;
		int endY = c + s - 1;
		
		//방향을 지원하는 배열들 (우,하,좌,상) - 시계방향
		int[] dr1 = {0,1,0,-1};
		int[] dc1 = {1,0,-1,0};
		
		int x = endX - startX;
		int y = endY - startY;
		
		//회전을 하는 배열의 수
		for(int i=0; i < (Math.min(x, y) / 2); i++) {
			int dir = 0;
			int r1 = startX + i;
			int c1 = startY + i;
			int prev = newMap[r1][c1];
			//방향을 다 돌았을 때 while문 반복 끝
			while(dir < 4) {
				r1 += dr1[dir];
				c1 += dc1[dir];
				//값을 벗어날 때
				if(r1 == (endX + 1 - i) || r1 < (startX + i) || c1 == (endY + 1 - i) || c1 < (startY + i)) {
					r1 -= dr1[dir];
					c1 -= dc1[dir];
					dir++;
					if(dir == 4) break;
					r1 += dr1[dir];
					c1 += dc1[dir];
				}
				int tempPrev = newMap[r1][c1]; 
				newMap[r1][c1] = prev;
				prev = tempPrev;
			}
		}
		
	}
	
}
