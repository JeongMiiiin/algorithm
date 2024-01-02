import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		R = r2 - r1;
		C = c2 - c1;
		map = new int[R + 1][C + 1];
		
		int idx = 1; //소용돌이를 채울 값
		//우 상 좌 하
		int[] dr1 = {0, -1, 0, 1};
		int[] dc1 = {1, 0, -1, 0};
		int maxDir = 1; //뻗어나갈 수 있는 개수 (좌를 만났을 때 +1)
		int dirCnt = 0; //현재 뻗은 개수
		int r = 0, c = 0, d= 0;
		
		while(!isFinish()) {
			
			//맵에 해당하는 숫자인 경우
			if(r >= r1 && r <= r2 && c >= c1 && c <= c2) map[r - r1][c - c1] = idx;
			idx++;
			
			r += dr1[d];
			c += dc1[d];
			
			if(++dirCnt == maxDir) { //방향을 바꿔야할 때
				if(++d == 4) d = 0;
				if(d == 2 || d == 0) maxDir++; //뻗어나갈 수 있는 개수를 늘려야할 때
				dirCnt = 0; //현재 뻗은 개수 초기화
			}
		}
		int numLen = (int) Math.log10(idx) + 1;
		for(int i=0; i <= R; i++) {
			for(int j=0; j <= C; j++) {
				if(j != 0) System.out.print(" ");
				int targetLen = (int) Math.log10(map[i][j]) + 1;
				while(targetLen++ < numLen) System.out.print(" ");
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		
		br.close();
	}
	
	private static boolean isFinish() {
		return (map[0][0] != 0 && map[R][0] != 0 && map[0][C] != 0 && map[R][C] != 0);
	}
}