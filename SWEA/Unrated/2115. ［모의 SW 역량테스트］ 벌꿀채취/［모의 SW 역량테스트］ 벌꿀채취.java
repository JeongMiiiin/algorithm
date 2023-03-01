import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * SWEA 2115 - 벌꿀채취
 * 주어지는 값
 * N : 배열의 크기
 * M : 두 명의 일꾼이 선택할 수 있는 용기 개수 (가로의 길이)
 * C : 일꾼이 채취할 수 있는 최대 양
 * 제약조건
 * 1. 최대 양을 초과하면 초과하지 않게 용기들 자체를 버려 양을 줄여야 한다.
 * 2. 두 일꾼은 겹치게 채취할 수 없다.
 * 상품의 가치는 각 용기마다 들어있는 꿀의 제곱의 합니다.
 * 최대수익을 출력하라
*/
public class Solution {
	static boolean[][] visited;
	static int[][] map;
	static int[][] work1;
	static int N, M, C, work1Cnt = -1, work2Cnt = -1, workNum = 1;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			//초기화
			work1Cnt = -1;
			work2Cnt = -1;
			workNum = 1;
			
			//입력
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			visited = new boolean[N][N];
			//주어진 배열 담기
			map = new int[N][N];
			for(int i=0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			work1 = new int[M][2];
			
			findWork(0);
			//첫번째 일꾼이 선택한 곳 방문처리
			for(int i=0; i < M; i++) visited[work1[i][0]][work1[i][1]] = true;	
			
			
			workNum++;
			
			findWork(0);
			
			//출력
			System.out.println("#" + t + " " + (work1Cnt + work2Cnt));
		}
	}
	private static void findWork(int r) {
		int c = -1;
		outer : while(c < N - M) {
			c++;
			int[] cnt = new int[M];
			int idx = 0;
			for(int i=c; i < M + c; i++) {
				if(visited[r][i]) continue outer;
				cnt[idx++] = map[r][i];
			}
			sumWork(cnt, r, c);
		}
		
		if(r < N - 1) findWork(r + 1);
			
	}
	
	private static void sumWork(int[] workList, int r, int c) {
		int tempSum = -1;
		for(int i=0; i < (1 << workList.length); i++) {
			int sum = 0, totalCnt = 0;
			for(int j=0; j < workList.length; j++) {
				if((i & (1 << j)) != 0) {
					totalCnt += workList[j];
					sum += workList[j] * workList[j];
				}
			}
			if(totalCnt <= C) tempSum = Math.max(tempSum, sum);
		}
		
		
		if(workNum == 1) {
			if(work1Cnt <= tempSum) {
				for(int i=0; i < M; i++) {
					work1[i][0] = r;
					work1[i][1] = c + i;
				}
				work1Cnt = tempSum;
			}
		} else work2Cnt = Math.max(work2Cnt, tempSum);
		
		
	}
}
