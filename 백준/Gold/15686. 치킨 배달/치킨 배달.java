import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 15686번 치킨 배달
 * 주어지는 값
 * N : 도시 크기
 * M : 최대로 있을 수 있는 치킨 점포 수
 * 도시에서
 * 0 : 빈칸
 * 1 : 집
 * 2 : 치킨점포
 * 각 집마다 치킨 점포와의 거리는 |r1 - r2| + |c1 - c2|
 * 도시의 치킨거리는 각 집의 최소 치킨거리의 합
 * M개이하로 치킨점포가 있을 때 최소 도시의 치킨거리를 구하라
 * 
*/
public class Main {
	static int N, M, R, chicken;
	static int ans = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] chickenPos;
	static int[] numbers;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		chickenPos = new int[13][2];
		
		chicken = 0;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j <=N; j++) {
				int target = Integer.parseInt(st.nextToken());
				if(target == 2) {
					chickenPos[chicken][0] = i;
					chickenPos[chicken][1] = j;
					chicken++;
				} 
				map[i][j] = target;
			}
		}
		
		if(chicken <= M) {
			//폐업 시켜야 하는 치킨집이 없을 때
			calcDistance();
		} else {
			R = chicken - M;
			numbers = new int[R];
			findNewMap(0,0);
		}
		
		System.out.println(ans);
	} 
	
	private static void findNewMap(int cnt, int start) {
		if(cnt == R) {
			for(int i= 0; i < R; i++) {
				int target = numbers[i] - 1;
				
				if(target < 0) continue;
				
				int r = chickenPos[target][0];
				int c = chickenPos[target][1];
				
				map[r][c] = 0;
			}
			calcDistance();
			for(int i= 0; i < R; i++) {
				int target = numbers[i] - 1;
				
				if(target < 0) continue;
				
				int r = chickenPos[target][0];
				int c = chickenPos[target][1];
				
				map[r][c] = 2;
			}
			
			return;
		}
		for(int i=start; i < chicken; i++) {
			numbers[cnt] = i + 1;
			findNewMap(cnt + 1, i + 1);
		}
	}

	private static void calcDistance() {
		int sum = 0;
		for(int i=1; i <= N; i++)
			for(int j=1; j <= N; j++)
				//집일때만 계산
				if(map[i][j] == 1) sum += findChicken(i, j);
		
		
		ans = Math.min(sum, ans);
	}
	
	private static int findChicken(int i, int j) {
		
		int res = Integer.MAX_VALUE;
		for(int z=0; z < chicken; z++) {
			int r = chickenPos[z][0];
			int c = chickenPos[z][1];
			if(map[r][c] == 2) {
				int temp = Math.abs(i - r) + Math.abs(j - c);
				res = Math.min(res, temp);
			}
		}
		
		return res;
	}
}
