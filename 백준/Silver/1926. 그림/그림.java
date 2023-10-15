import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 1926 - 그림
 * 도화지에 그림이 그려져 있을 때 그 그림의 개수와 가장 넓은 그림을 출력하라
 * 그림은 사방으로 연결되어있는 그림을 하나로 한다.
 * 1. 맵 세팅
 * 2. BFS
 * */
public class Main {
	private static int R, C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//가로 세로 길이 세팅
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		//도화지 세팅
		int[][] drawingPaper = new int[R][C];
		for(int i=0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < C; j++) drawingPaper[i][j] = Integer.parseInt(st.nextToken()); 
		}
		
		Queue<Point> q = new LinkedList<>();
		Point cur;
		int nr, nc;
		
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		
		//결과값 0번째 : 그림 개수, 1번째 : 가장 넓은 그림 넓이
		int[] result = {0, 0};
		//방문 배열 선언
		boolean[][] visited = new boolean[R][C];
		//체크
		for(int i=0; i < R; i++) {
			for(int j=0; j < C; j++) {
				if(visited[i][j] || drawingPaper[i][j] == 0) continue; //이미 방문했거나 그림이 없으면 패스
				
				//그림이 있는 경우
				result[0]++;
				int cnt = 1;
				visited[i][j] = true;
				q.add(new Point(i, j));
				
				while(!q.isEmpty()) {
					cur = q.poll();
					//사방 탐색
					for(int d=0; d < 4; d++) {
						nr = cur.x + dr1[d];
						nc = cur.y + dc1[d];
						//맵에서 벗어나지 않고, 방문하지 않고, 그림이 있는 경우
						if(!outMap(nr, nc) && !visited[nr][nc] && drawingPaper[nr][nc] == 1) {
							cnt++;
							visited[nr][nc] = true;
							q.add(new Point(nr, nc));
						}
					}
				}
				if(cnt > result[1]) result[1] = cnt;
			}
		}
		
		bw.write(String.valueOf(result[0]) + "\n");
		bw.write(String.valueOf(result[1]) + "\n");
		
		bw.close();
		br.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
}