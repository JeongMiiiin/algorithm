import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 6593 - 상범빌딩
 * 탈출하는 가장 빠른 길을 찾아라
 * 상범 빌딩은 각 변의 길이가 1인 정육면체로 이루어짐.
 * 각 정육면체는 금으로 이루어져 있어 지나갈 수 없거나, 비어있어서 지나갈 수 있음.
 * 당신은 각 칸에서 인접한 6개의 칸(동, 서, 남, 북, 상, 하)로 1분의 시간을 들여 이동 가능.
 * 단, 대각선과 바깥은 불가
 * 
 * L : 빌딩의 층 수
 * R : 한 층의 행 개수
 * C : 한 층의 열 개수
 * */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			if(L + R + C == 0) break;
			
			Queue<Point> q = new LinkedList<>();
			//빌딩 세팅
			char[][] building = new char[L * R][C];
			int idx = 0;
			while(idx < L) {
				for(int i=0; i < R; i++) {
					int j = 0;
					for(char c : br.readLine().toCharArray()) {
						building[i + idx * R][j] = c;
						if(c == 'S') q.add(new Point(i + idx * L,j)); //출발점인 경우 q에 담기
						j++;
					}
					
				}
				
				idx++;
				br.readLine(); //빈 칸 없애기
			}
			
			//동 서 남 북 상 하
			int[] dr1 = {0, 0, -1, 1, -R, R};
			int[] dc1 = {1, -1, 0, 0, 0, 0};
			
			//방문처리할 배열
			boolean[][] visited = new boolean[L * R][C];
			
			//검증
			boolean flag = false;
			int minute = 0, nr, nc;
			Point cur = q.peek();
			visited[cur.x][cur.y] = true;
			
			outer : while(!q.isEmpty()) {
				minute++; //분 증가
				int size = q.size();
				for(int i=0; i < size; i++) {
					cur = q.poll();
					for(int d=0; d < 6; d++) {
						nr = cur.x + dr1[d];
						nc = cur.y + dc1[d];
						 //빌딩에서 벗어나거나 방문했거나 금이면 패스
						if(nr < 0 || nr >= R * L || nc < 0 || nc >= C || visited[nr][nc] || building[nr][nc] == '#') continue;
						//동 서 남 북으로 층을 옮겼으면 패스
						if(d < 4 && cur.x / R != nr / R) continue;
						
						//출구에 도착했으면 종료
						if(building[nr][nc] == 'E') {
							flag = true;
							break outer;
						} else {
							//방문처리
							visited[nr][nc] = true;
							q.add(new Point(nr, nc));
						}
						
					}
				}
			}
			
			if(flag) bw.write("Escaped in " + String.valueOf(minute) + " minute(s).\n");
			else bw.write("Trapped!\n");
		}
		
		bw.close();
		br.close();
	}
}