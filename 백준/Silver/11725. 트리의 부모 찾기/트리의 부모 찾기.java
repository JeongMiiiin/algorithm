import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 백준 11725 - 트리의 부모 찾기
 * 루트 없는 트리가 주어짐.
 * 트르의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하라.
 * */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		ArrayList<Integer>[] map = new ArrayList[N + 1];
		for(int i=1; i <= N; i++) map[i] = new ArrayList<>();
		for(int i=0; i < N - 1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a].add(b);
			map[b].add(a);
		}
		Queue<Integer> q = new LinkedList<>();
		int[] result = new int[N + 1];
		q.add(1);
		int cur;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i < size; i++) {
				cur = q.poll();
				for(int next : map[cur]) {
					if(result[next] == 0) { //아직 방문하지 않은 곳일 경우
						result[next] = cur;
						q.add(next);
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=2; i <= N; i++) sb.append(result[i] + "\n");
		System.out.println(sb.toString());
		sc.close();
	}
}