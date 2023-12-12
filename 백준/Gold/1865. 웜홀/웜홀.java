import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 1865 - 웜홀
 * 월드나라에는 N개의 지점이 있음.
 * N개의 지점 사이에는 M개의 도로와 W개의 웜홀이 존재.
 * 도로는 방향이 없으며 웜홀은 방향이 존재.
 * 웜홀을 지나면 특이하게 시작을 했을 때보다 시간이 뒤로 가게 된다.
 * 한 지점에서 출발해 다시 출발하는 위치로 돌아왔을 때, 출발했을때보다 시간이 되돌아간 경우가 있는지 궁금해짐.
 * */

public class Main {
	static class Road {
		int start, end, cost;
		public Road(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int INF = Integer.MAX_VALUE;
		int[] dist;
		Road[] roads;
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			dist = new int[N + 1];
			Arrays.fill(dist, INF);
			roads = new Road[M * 2 + W];
			int i = 0, start, end, cost;
			//도로 세팅
			while(i < M * 2) {
				st = new StringTokenizer(br.readLine(), " ");
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				roads[i++] = new Road(start, end, cost);
				roads[i++] = new Road(end, start, cost);
			}
			while(i < M * 2 + W) {
				st = new StringTokenizer(br.readLine(), " ");
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				roads[i++] = new Road(start, end, cost * -1);
			}
			boolean timeMachineFlag = false;
			Road cur;
			dist[1] = 0;
			//최단경로 갱신
			for(i=1; i < N; i++) {
				for(int j=0; j < M * 2 + W; j++) {
					cur = roads[j];
					if(dist[cur.start] != INF && dist[cur.end] > dist[cur.start] + cur.cost) dist[cur.end] = dist[cur.start] + cur.cost;
				}
			}
			
			//들리지 않은 지점이 있을 때 다시한번 확인
			for(i=1; i <= N; i++) {
				if(dist[i] != INF) continue; //이미 최단경로로 갱신된 곳이면 패스
				dist[i] = 0;
				for(int j=1; j < N; j++) {
					for(int z=0; z < M * 2 + W; z++) {
						cur = roads[z];
						if(dist[cur.start] != INF && dist[cur.end] > dist[cur.start] + cur.cost) dist[cur.end] = dist[cur.start] + cur.cost;
					}
				}
			}
			
			//타임머신 있는지 확인
			for(i=0; i < M * 2 + W; i++) {
				cur = roads[i];
				if(dist[cur.start] != INF && dist[cur.end] > dist[cur.start] + cur.cost) {
					timeMachineFlag = true;
					break;
				}
			}
			
			String result = "YES";
			if(!timeMachineFlag) result = "NO";
			System.out.println(result);
		}
		
		br.close();
	}
}