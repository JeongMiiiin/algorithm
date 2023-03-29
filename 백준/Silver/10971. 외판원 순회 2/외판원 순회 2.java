import java.util.Scanner;

/*
 * 백준 10971 - 외판원 순회 2
 * 
 * Traveling Salesman problem (TSP)
 * 1번부터 N번까지 번호가 매겨져 있는 도시들
 * 도시들 사이에는 길이 있음. (없을 수도 있음)
 * 어느 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 순회 여행 경로를 계획
 * 마지막을 제외하고 갔던 도시로 돌아오는 여행 계획에서 최소 비용을 구하라
 * 
 * 1. 순열로 경우의 수를 뽑아냄
 * 2. 1 - 2 -> DP 저장?
 * 
*/
public class Main {
	static int N, totalCost = Integer.MAX_VALUE;
	static int[][] map;
	static int[] numbers;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		numbers = new int[N + 1];
		for(int i=1; i <= N; i++) for(int j=1; j <= N; j++) map[i][j] = sc.nextInt();
		
		perm(0);
		
		System.out.println(totalCost);
		
		sc.close();
	}
	
	private static void perm(int cnt) {
		if(cnt == N) {
			numbers[N] = numbers[0];
			find();
			return;
		}
		
		for(int i=1; i <= N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			numbers[cnt] = i;
			perm(cnt + 1);
			visited[i] = false;
		}
		
	}
	
	private static void find() {
		int cost = 0;
		for(int i=0; i < N; i++) {
			int prev = numbers[i];
			int next = numbers[i + 1];
            if(map[prev][next] == 0) return;
			cost += map[prev][next];
			if(cost > totalCost) return;
		}
		totalCost = Math.min(cost, totalCost);
	}
	
}