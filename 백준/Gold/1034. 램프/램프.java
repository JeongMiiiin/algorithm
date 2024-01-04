import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		int result = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		//맵 세팅
		int[][] map = new int[N][M];
		for(int i=0; i < N; i++) {
			String s = br.readLine();
			for(int j=0; j < M; j++) map[i][j] = s.charAt(j) - '0';
		}
		
		int K = Integer.parseInt(br.readLine());
		
		for(int i=0; i < N; i++) {
			int count = 0;
			for(int j=0; j < M; j++) if(map[i][j] == 0) count++;
			
			int samePattern = 0;
			
			if(count <= K && count % 2 == K % 2) { // 해당 행의 패턴을 다 뒤집을 수 있는 경우
				for(int j=0; j < N; j++) {
					boolean flag = true;
					for(int nj=0; nj < M; nj++) {
						if(map[i][nj] != map[j][nj]) {
							flag = false;
							break;
						}
					}
					if(flag) samePattern++;
				}
			}
			result = Math.max(result, samePattern);
		}
		
		System.out.println(result);
		br.close();
	}
}