import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 1025 - 제곱수 찾기
 * N행 M열의 표 A.
 * 표의 각 칸에는 숫자가 적혀 있음.
 * 연두는 서로 다른 1개 이상의 칸을 선택하려고 한다.
 * 행의 번호가 선택한 순서대로 등차수열을 이뤄야 하고, 열의 번호도 선택한 순서대로 등차수열을 이뤄야 함.
 * 이렇게 선택한 칸데 적힌 수를 순서대로 이어붙이면 정수를 하나 만들 수 있다.
 * 만들 수 있는 정수 중에서 가장 큰 완전 제곱수를 구해보자. 완전 제곱수란 어떤 정수를 제곱한 수이다.
 * */
public class Main {
	static int N, M, result;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		result = -1;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//맵 세팅
		map = new int[N + 1][M + 1];
		for(int i=1; i <= N; i++) {
			int j = 1;
			for(char c : br.readLine().toCharArray()) map[i][j++] = c - '0';
		}
		
		for(int i=1; i <= N; i++) {
			for(int j=1; j <= M; j++) {
				updateResult(map[i][j]);
				//보내기
				for(int plusR = -(N - 1); plusR <= N - 1; plusR++) {
					for(int plusC = -(M - 1); plusC <= M - 1; plusC++) {
						if(plusR == 0 && plusC == 0) continue; //둘다 0인 경우는 패스
						StringBuilder sb = new StringBuilder();
						sb.append(map[i][j]);
						find(i, plusR, j, plusC, sb);
					}
				}
			}
		}
		
		System.out.println(result);
		br.close();
	}
	
	private static void find(int curR, int plusR, int curC, int plusC, StringBuilder target) {
		updateResult(Integer.parseInt(target.toString()));
		
		int nr = curR + plusR;
		int nc = curC + plusC;
		
		if(!outMap(nr ,nc)) { //진행이 가능할 때
			StringBuilder sb = new StringBuilder(target.toString());
			sb.append(map[nr][nc]);
			find(nr, plusR, nc, plusC, sb);
		}
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 1 || r > N || c < 1 || c > M);
	}
	
	private static void updateResult(int target) {
		if(result >= target) return;
		int square = (int) Math.sqrt(target);
		if(target == square * square) result = target;
	}
}