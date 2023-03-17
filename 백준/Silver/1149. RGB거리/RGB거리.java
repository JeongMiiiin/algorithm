import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 1149 - RGB거리
 * 
 * 주어지는 값
 * N : 집의 수
 * N개의 줄 : 각 집을 빨강, 초록, 파랑으로 칠하는 비용
 * 
 * RGB 거리에는 집이 N개. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있음
 * 
 * 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 함.
 * 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때,
 * 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
 * 1. 1번 집의 색은 2번 집의 색과 같지 않아야 함.
 * 2. N번 집의 색은 N - 1번 집의 색과 같지 않아야 한다.
 * 3. i번 집의 색은 i - 1, i + 1번 집의 색과 같지 않아야 한다. 
 * 
 * 이차원 배열로 선택한 값과 그 동안의 최소비용 합 담기
 * 첫번째가 빨강, 초록, 파랑으로 시작하는 것 해보기
 * 
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static long ans = Long.MAX_VALUE;
	static int[][] colorCost;
	public static void main(String[] args) throws Exception {
		
		N = Integer.parseInt(br.readLine());
		colorCost = new int[N + 1][3];
		
		for(int i=1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			colorCost[i][0] = Integer.parseInt(st.nextToken());
			colorCost[i][1] = Integer.parseInt(st.nextToken());
			colorCost[i][2] = Integer.parseInt(st.nextToken());
		}
	    
        for(int i=2; i <= N; i++){
            colorCost[i][0] += Math.min(colorCost[i - 1][1], colorCost[i - 1][2]);
			colorCost[i][1] += Math.min(colorCost[i - 1][0], colorCost[i - 1][2]);
			colorCost[i][2] += Math.min(colorCost[i - 1][1], colorCost[i - 1][0]);
        }
	
		
		System.out.println(Math.min(Math.min(colorCost[N][0],colorCost[N][1]), colorCost[N][2]));
	}
	
}