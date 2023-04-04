import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 3055 - 탈출
 * 
 * R x C로 이루어진 지도
 * 맵의 상태
 * 1 . -> 빈 칸  2. * -> 물이 차있음  3. X -> 돌  4. D -> 비버의 굴  5. S -> 고슴도치 위치
 * 매 분마다 고슴도치는 인접한 4방향으로 움직일 수 있음
 * 매 분마다 물도 인접한 4방향의 빈칸으로 물을 보냄.
 * 물과 고슴도치는 돌을 통과할 수 없으며, 고슴도치는 물로 차있는 구역으로 이동할 수 없고,
 * 물도 비버의 소굴로 이동할 수 없다.
 * 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없음.
 * 
 * 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간 출력.
 * 만약, 비버의 굴로 이동할 수 없을 때는 "KAKTUS"를 출력.
 * 
 * BFS로 구현
*/
public class Main {
	//고슴도치를 관리하는 클래스
	static class HedgeDog {
		int r, c;
		public HedgeDog(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Water {
		int r, c;

		public Water(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int R, C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];
		boolean[][] hedgeDogVisited = new boolean[R][C];
		Queue<Water> wq = new LinkedList<>();
		
		int finalR = 0, finalC = 0;
		
		HedgeDog hd = null;
		//맵세팅
		for(int i=0; i < R; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j=0; j < C; j++) {
				map[i][j] = judgeMapStatus(c[j]);
				//물로 이루어진 곳일 때
				if(map[i][j] == 2) wq.offer(new Water(i, j));
				//고슴도치가 있는 곳일 때
				if(map[i][j] == 3) hd = new HedgeDog(i, j);
				//비버의 굴이 있는 곳일 때
				if(map[i][j] == 4) {
					finalR = i;
					finalC = j;
				}
			}
		}
		
		Queue<HedgeDog> q = new LinkedList<>();
		q.offer(hd);
		hedgeDogVisited[hd.r][hd.c] = true;
		map[hd.r][hd.c] = 0;
		
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		
		int time = 0, size, nr, nc;
		Water wCur;
		HedgeDog cur;
		boolean flag = false;
		outer : while(!q.isEmpty()) {
			
			//물 먼저 퍼트리기
			size = wq.size();
			for(int i=0; i < size; i++) {
				wCur = wq.poll();
				for(int d= 0; d < 4; d++) {
					nr = wCur.r + dr1[d];
					nc = wCur.c + dc1[d];
					//물이 퍼질 수 있는 곳일 때
					if( !outMap(nr, nc) && map[nr][nc] == 0) {
						map[nr][nc] = 2;
						wq.offer(new Water(nr, nc));
					}
				}
			}
			//고슴도치 움직이기
			size = q.size();
			for(int i=0; i < size; i++) {
				cur = q.poll();
				for(int d= 0; d < 4; d++) {
					nr = cur.r + dr1[d];
					nc = cur.c + dc1[d];
					
					//움직일 수 있는 곳일 때
					if( !outMap(nr, nc) && map[nr][nc] != 2 && map[nr][nc] != 1 && !hedgeDogVisited[nr][nc]) {
						hedgeDogVisited[nr][nc] = true;
						if(nr == finalR && nc == finalC) {
							flag = true;
							time++;
							break outer;
						} else q.offer(new HedgeDog(nr, nc));
					}
				}
			}
			time++;
		}
		
		if(flag) bw.write(time + "\n");
		else bw.write("KAKTUS\n");
		
		bw.close();
	}
	
	private static int judgeMapStatus(char c) {
		if(c == '.') return 0; //빈칸
		else if(c == 'X') return 1; //돌
		else if(c == '*') return 2; //물
		else if(c == 'S') return 3; //고슴도치
		else return 4; //비버의 굴
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
}