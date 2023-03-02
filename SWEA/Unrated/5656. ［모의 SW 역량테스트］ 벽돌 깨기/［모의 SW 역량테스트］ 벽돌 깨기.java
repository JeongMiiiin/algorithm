import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * SWEA 5656 - 벽돌 깨기
 * 주어지는 값
 * N : 구슬 쏘기 횟수
 * W : 맵의 가로 크기
 * H : 맵의 세로 크기
 * 
 * 게임 규칙
 * 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있음
 * 벽돌은 숫자 1 ~ 9 로 표현되며, 명중한 벽돌의 상하좌우로 (벽돌 숫자 - 1)칸 만큼 같이 제거
 * 제거되는 범위 내에 있는 벽돌은 동시에 제거 됨
 * 
 * N번을 떨어트려본다.
 * 1번부터 하면서 가장 최적의 값을 찾는다?
 * 1. 구슬을 처음부터 한번씩 떨어트리면서 가장 많이 깨지는 값을 찾아 계속 boolean값을 갱신
 * 
*/
public class Solution {
	static int N, W, H;
	static int[][] map;
	static int[] numbers;
	static int brickCnt, crashCnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			//초기화
			brickCnt = 0;
			crashCnt = 0;
			st = new StringTokenizer(br.readLine(), " ");
			//입력
			N = Integer.parseInt(st.nextToken());
			numbers = new int[N];
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			for(int i=0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 0) brickCnt++; //벽돌일 때 그 값을 증가
				}
			}
			
			dupliPerm(0);
			
			int ans = brickCnt - crashCnt > 0 ? brickCnt - crashCnt : 0;
			
			//출력
			bw.write("#" + t + " " + ans + "\n");
		}
		
		bw.close();
	}
	
	private static void dupliPerm(int cnt) {
		if(brickCnt <= crashCnt) return;
		
		if(cnt == N) {
			simulateCrash();
			return;
		}
		for(int i=0; i < W; i++) {
			numbers[cnt] = i;
			dupliPerm(cnt + 1);
		}
	}
	
	private static void simulateCrash() {
		int[][] tempMap = new int[H][W];
		for(int i=0; i < H; i++) tempMap[i] = Arrays.copyOf(map[i], W);
		boolean[][] checkMap = new boolean[H][W];
		
		int tempCnt = 0;
		for(int i=0; i < N; i++) {
			int target = numbers[i];
			downMap(tempMap, checkMap);
			checkMap = new boolean[H][W];
			tempCnt += findBrick(target, tempMap, checkMap);
		}
		
		if(tempCnt > crashCnt) crashCnt = tempCnt;
	
	}
	
	private static int findBrick(int start, int[][] tempMap, boolean[][] checkMap) {
		int idx = 0;
		int target = tempMap[idx][start];
		
		while(idx < H ) { //해당 열 아래로 구슬 떨어트려서 가장 먼저 만나는 벽돌 찾기
			if( tempMap[idx][start] > 0) {
				target = tempMap[idx][start];
				break;
			}
			idx++;
		}
		if(target == 0) return target; //최초로 만난 벽돌의 숫자가 1이거나 마지막까지 떨어진 경우 target값 리턴
		
		int result = 1;
		
		checkMap[idx][start] = true;
		
		if(target == 1) {return result;}
		
		return result += explodeCnt(idx, start, tempMap, checkMap); 
		
	}
	
	//상 우 하 좌
	static int[] dr1 = {-1,0,1,0};
	static int[] dc1 = {0,1,0,-1};
	private static int explodeCnt(int r, int c, int[][] tempMap, boolean[][] checkMap) {
		int nr = r;
		int nc = c;
		int cnt = 0;
		int go = tempMap[nr][nc] - 1;
		for(int d=0; d < 4; d++) {
			for(int i=0; i < go; i++) {
				nr += dr1[d];
				nc += dc1[d];
				if(nr < 0 || nr >= H || nc < 0 || nc >= W) break; //배열을 벗어난 경우
				if(tempMap[nr][nc] == 0) continue; //빈칸인 경우
				if(checkMap[nr][nc]) continue;
				checkMap[nr][nc] = true;
				
				cnt++;
				
				//1이 아닌 경우 연속 폭발
				if(tempMap[nr][nc] != 1) cnt += explodeCnt(nr, nc, tempMap, checkMap);
			}
			nr = r;
			nc = c;
		}
		return cnt;
	}

	private static void downMap(int[][] tempMap, boolean[][] checkMap) {
		for(int i=0; i < W; i++) {
			for(int j=0; j < H; j++) {
				if(checkMap[j][i]) { //부셔야 하는 벽돌을 만났으면 그 전것부터 다 땡기기
					int prev = j - 1;
					int cur = j;
					while(prev >= 0) tempMap[cur--][i] = tempMap[prev--][i];
					
					tempMap[0][i] = 0;
				}
			}
		}
	}
	
}
