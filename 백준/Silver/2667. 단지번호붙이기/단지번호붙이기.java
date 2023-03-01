import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 백준 2667번 단지번호붙이기
 * 정사각형 모양의 지도
 * 1 : 집이 있는 곳
 * 0 : 집이 없는 곳
 * 단지의 조건 : 상하좌우의 연결된 집들의 묶음
 * 총 단지 개수
 * 각 단지마다 속한 집의 개수 출력
*/
public class Main {
	static int N, totalNum, current = 1;
	static int[][] map;
	static Map<Integer,Integer> complexList = new HashMap<Integer,Integer>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		//맵 만들기
		for(int i=0; i < N; i++) {
			String s = br.readLine();
			for(int j=0; j < N; j++) map[i][j] = s.charAt(j) - '0';
		}
		
		for(int i=0; i < N; i++) {
			for(int j=0; j < N; j++) {
				bfs(i, j, current + 1);
			}
		}
		System.out.println(totalNum);
		List<Integer> valueList = new ArrayList(complexList.values());
		Collections.sort(valueList);
		for(int i=0; i < valueList.size(); i++)
			System.out.println(valueList.get(i));
	}
	
	private static void bfs(int r, int c, int orderNum) {
		if(map[r][c] > 1 || map[r][c] == 0) return;
		
		if(current != orderNum) {
			current = orderNum;
			totalNum++;
		}
		
		map[r][c] = orderNum;
		complexList.put(orderNum - 1, complexList.getOrDefault(orderNum - 1, 0) + 1);
		
		//상, 우, 하, 좌 
		int[] dr1 = {-1,0,1,0};
		int[] dc1 = {0,1,0,-1};
		for(int i=0; i < 4; i++) {
			int nr = r + dr1[i];
			int nc = c + dc1[i];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue; //배열을 벗어난 경우
			if(map[nr][nc] == 1) bfs(nr, nc, orderNum);
		}
	}
}
