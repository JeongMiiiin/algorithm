import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 22352 - 항체 인식
 *
 * 주어지는 값
 * N : 세로 크기
 * M : 가로 크기
 * N개의 줄 : 백신을 놓기 전의 촬영 결과
 * N개의 줄 : 백신을 놓은 후의 촬영 결과
 *
 * CPCU-1202 백신을 놓으면, 격자의 칸 중 하나에 항체가 생성됨.
 * 이 항체는 현재 속해 있는 칸과 같은 데이터 값을 가지면서 상하좌우로 인접한 칸이 있을 경우 그 칸으로 퍼짐.
 * 항체가 더 이상 퍼져나갈 수 없게 되면, 항체는 조직에 완전히 스며들고, 어떤 동일한 값으로 조직 전체가 새로 업데이트.
 * 이때, 우연히 원래의 데이터 값과 업데이트된 데이터 값이 동일할 수 있다.
 * 하나의 조직에 백신을 놓기 전 촬영 결과와 백신을 놓은 뒤의 촬영 결과가 한 쌍으로 이루어짐.
 * 두 장의 촬영 결과가 주어질 때, 이 조직에 놓은 백신이 CPCU-1202 백신일 가능성을 판단.
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static class Cell{
		int r, c;

		public Cell(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int N, M;
	static int[][] before, after;
	static boolean[][] visited;
	static boolean flag = false;
 	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		before = new int[N][M];
		after = new int[N][M];
		visited = new boolean[N][M];
		
		settingMap(before);
		settingMap(after);
		
		//이미 동일할 때
		if(compare(before)) flag = true;
		
		//BFS로 주변 탐색으로 백신 퍼지는 것 해보기
		outer : for(int i=0; i < N; i++) {
			for(int j=0; j < M; j++) {
				if(flag) break outer;
				if(!visited[i][j] && before[i][j] != after[i][j]) care(i, j);
			}
		}
			
		String ans = "YES";
		if(!flag) ans = "NO";
		System.out.println(ans);
	}
	
 	//상 우 하 좌
 	static int[] dr1 = {-1, 0, 1, 0};
 	static int[] dc1 = {0, 1, 0, -1};
 	private static void care(int i, int j) {
 		
 		int[][] compareMap = new int[N][M];
 		copyMap(before, compareMap);
 		
 		int prev = compareMap[i][j];
 		int future = after[i][j];
 		
 		Queue<Cell> q = new ArrayDeque<>();
 		q.offer(new Cell(i, j));
 		visited[i][j] = true;
 		
 		Cell c;
 		int nr, nc;
 		while(!q.isEmpty()) {
 			c = q.poll();
 			compareMap[c.r][c.c] = future;
 			for(int d=0; d < 4; d++) {
 				nr = c.r + dr1[d];
 				nc = c.c + dc1[d];
 				if(outMap(nr,nc) || visited[nr][nc]) continue; //배열을 벗어났거나 이미 방문한 경우 패스
 				if(compareMap[nr][nc] == prev) {
 					visited[nr][nc] = true;
 					q.offer(new Cell(nr, nc));
 				}
 			}
 		}
 		
 		if(compare(compareMap)) flag = true;
	}

	private static void settingMap(int[][] map) throws IOException {
 		for(int i=0; i < N; i++) {
 			st = new StringTokenizer(br.readLine(), " ");
 			for(int j=0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
 		}
 	}
	
	private static void copyMap(int[][] originMap, int[][] copyMap) {
		for(int i=0; i < N; i++) for(int j=0; j < M; j++) copyMap[i][j] = originMap[i][j];
	}
	
	private static boolean compare(int[][] compareMap) {
		boolean result = true;
		outer : for(int i=0; i < N; i++) {
			for(int j=0; j < M; j++) {
				if(compareMap[i][j] != after[i][j]) {
					result = false;
					break outer;
				}
			}
		}
		
		
		return result;
	}
 	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M);
	}
}