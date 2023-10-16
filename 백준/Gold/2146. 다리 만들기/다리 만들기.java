import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 2146 - 다리 만들기
 * 한 섬과 다른 섬을 잇는 다리 하나 중 가장 짧은 다리 건설
 * 
 * N x N 크기의 나라
 * 여러 섬으로 이루어짐
 * 0은 바다 1은 섬
 * 
 * 1. 맵 세팅
 * 2. 섬에 있는 곳들 표시 해주기
 * 3. 각 섬에서 출발해 가장 먼저 만나는 섬 값 갱신
 * 
 * */

public class Main {
	static class Island implements Comparable<Island>{
		int r, c, dist;
		Island(int r, int c, int dist){
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
		@Override
		public int compareTo(Island o) {
			return this.dist - o.dist;
		}
	}
	
	
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		StringTokenizer st;
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		
		Queue<Island> q = new LinkedList<>();
		
		//방문처리할 배열
		boolean[][] visited = new boolean[N][N];
		int idx = 1, nr, nc;
		Island cur;
		for(int i=0; i < N; i++) {
			for(int j=0; j < N; j++) {
				//방문했거나 바다인 경우 패스
				if(visited[i][j] || map[i][j] == 0) continue;
				visited[i][j] = true;
				map[i][j] = idx;
				q.add(new Island(i, j, 0));
				while(!q.isEmpty()) {
					cur = q.poll();
					for(int d=0; d < 4; d++) {
						nr = cur.r + dr1[d];
						nc = cur.c + dc1[d];
						if(outMap(nr, nc) || map[nr][nc] == 0 || visited[nr][nc]) continue;
						visited[nr][nc] = true;
						map[nr][nc] = idx;
						q.add(new Island(nr, nc, 0));
					}
				}
				idx++;
			}
		}
		
		int result = Integer.MAX_VALUE;
		
		PriorityQueue<Island> pq = new PriorityQueue<>();
		//가장 짧은 다리 찾기
		for(int i=1; i < idx; i++) {
			//방문처리 초기화
			for(int j=0; j < N; j++) Arrays.fill(visited[j], false);
			pq.clear();
			for(int j=0; j < N; j++) {
				for(int z=0; z < N; z++) {
					//해당되는 섬이 아닌 경우
					if(visited[j][z] || map[j][z] != i) continue;
					visited[j][z] = true;
					pq.add(new Island(j, z, 0));
				}
			}
			int dist = Integer.MAX_VALUE;
			while(!pq.isEmpty()) {
				cur = pq.poll();
				if(cur.dist >= result || cur.dist >= dist) break; //이미 현재 최소값보다 크거나 같을 경우 종료
				
				for(int d=0; d < 4; d++) {
					nr = cur.r + dr1[d];
					nc = cur.c + dc1[d];
					if(outMap(nr,nc) || visited[nr][nc]) continue; //맵에서 벗어나거나 이미 방문한 곳인 경우
					visited[nr][nc] = true;
					if(map[nr][nc] == 0) pq.add(new Island(nr, nc, cur.dist + 1));
					else dist = cur.dist;
				}
			}
			
			result = Math.min(result, dist);
		}
		
		bw.write(String.valueOf(result));
		bw.close();
		br.close();
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
}