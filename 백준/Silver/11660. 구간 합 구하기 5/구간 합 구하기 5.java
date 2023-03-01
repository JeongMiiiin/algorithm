import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	/*
	 * BOJ 11660 구간 합 구하기
	 * 첫째 줄 -> N : 맵 크기, M : 구해야 하는 횟수
	 * 둘째 줄 부터 N번째 줄 -> 맵에 할당하는 값
	 * N + 1번째 줄부터 M번째 줄 -> 구해야 하는 구간 값
	*/
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
            	map[i][j] = map[i][j - 1] + Integer.parseInt(st.nextToken());
            }
        }
		
		for(int i=0; i< M; i++) {
			st = new StringTokenizer(br.readLine());
			int sX = Integer.parseInt(st.nextToken());
			int sY = Integer.parseInt(st.nextToken());
			
			int eX = Integer.parseInt(st.nextToken());
			int eY = Integer.parseInt(st.nextToken());
			
			int sum = 0;
			for (int j = sX; j <= eX; j++) {
                sum += map[j][eY] - map[j][sY - 1];
            }
			
			
			bw.write(sum + "\n");
			
		}
		
		bw.close();
		
	}
}
