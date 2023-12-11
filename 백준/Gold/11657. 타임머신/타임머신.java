import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 11657 - 타임머신
 * 한 도시에서 출발하여 다른 도시에 도착하는 버스 M개가 있다.
 * 각 버스는 A, B, C로 나타낼 수 있는데, A는 시작도시, B는 도착도시, C는 버스를 타고 이동하는데 걸리는 시간이다.
 * C가 양수가 아닌 경우도 있다. 0은 순간이동. C < 0은 타임머신으로 시간을 되돌아가는 경우다.
 * 1번 도시에서 출발해 나머지 도시로 가는 가장 빠른 시간을 구하라.
 * 첫째 줄에 도시의 개수 N, 버스 노선의 개수 M이 주어짐.
 * 둘째 줄부터 M개의 줄에는 버스 노선의 정보 A, B, C가 주어짐.
 * 만약 1번 도시에서 출발해 어떤 도시로 가는 과정에서 무한히 오래 전으로 되돌릴 수 있다면 첫째 줄에 -1 출력.
 * 그렇지 않다면 N - 1개 줄에 걸쳐 각 줄에 1번 도시에서 출발해 2번 도시, 3번 도시, ..., N번 도시로 가는 가장 빠른 시간을 순서대로 출력.
 * 만약 해당 도시로 가는 경로가 없다면 대신 -1 출력.
 * */

public class Main {
	static class Bus {
		int start, end, cost;
		public Bus(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		final long INF = Long.MAX_VALUE;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] result = new long[N + 1];
		Arrays.fill(result, INF);
		result[1] = 0;
		Bus[] buses = new Bus[M];
		
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			buses[i] = new Bus(A, B, C);
		}
		StringBuilder sb = new StringBuilder();
		boolean timeMachineFlag = false;
		Bus cur;
		for(int i=1; i < N; i++) {
			for(int j=0; j < M; j++) {
				cur = buses[j];
				if(result[cur.start] != INF && result[cur.end] > result[cur.start] + cur.cost) result[cur.end] = result[cur.start] + cur.cost;
			}
		}
		
		//타임머신 있는지 확인
		for(int i=0; i < M; i++) {
			cur = buses[i];
			if(result[cur.start] != INF && result[cur.end] > result[cur.start] + cur.cost) {
				timeMachineFlag = true;
				break;
			}
		}
		
		if(timeMachineFlag) System.out.println(-1);
		else {
			for(int i=2; i <= N; i++) {
				sb.append(result[i] == INF ? -1 + "\n" : result[i] + "\n");
			}
			System.out.println(sb.toString());
		}
		
		br.close();
	}
}