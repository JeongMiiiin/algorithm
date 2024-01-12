import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/*
 * 백준 2251 - 물통
 * 각각 부피가 A, B, C 리터인 3개의 물통이 있음.
 * 앞의 두 물통은 비어 있음. 세 번째 물통은 가득 차 있음.
 * 어떤 물통을 다른 물통으로 쏟아 부을 수 있는데, 이때 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 부을 수 있다.
 * 이와 같은 과정을 거치다 보면 세 번째 물통에 담겨있는 물의 양이 변할 수 있다.
 * 첫 번째 물통이 비어 있을 때, 세 번째 물통에 담겨 있을 수 있는 물의 양을 모두 구하라.
 * */

public class Main {
	static int[] map;
	static boolean[][] visited;
	static Set<Integer> result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[3];
		for(int i=0; i < 3; i++) map[i] = sc.nextInt();
		visited = new boolean[201][201];
		result = new TreeSet<>();
		dfs(0, 0, map[2]);
		
		StringBuilder sb = new StringBuilder();
		for(int num : result) sb.append(num + " ");
		System.out.println(sb.toString());
		sc.close();
	}
	static void dfs(int a, int b, int c) {
		if(visited[a][b]) return; //이미 방문한 양인 경우
		
		if(a == 0) result.add(c); //조건에 부합하는 경우
		
		visited[a][b] = true;
		//0 -> 1
		if(a + b > map[1]) dfs((a + b) - map[1], map[1], c);
		else dfs(0, a + b, c);
		
		//1 -> 0
		if(a + b > map[0]) dfs(map[0], a + b - map[0], c);
		else dfs(a + b, 0, c);
		
		//2 -> 0
		if(a + c > map[0]) dfs(map[0], b, a + c - map[0]);
		else dfs(a + c, b, 0);
		
		//2 -> 1
		if(b + c > map[1]) dfs(a, map[1], b + c - map[1]);
		else dfs(a, b + c, 0);
		
		//1 -> 2
		dfs(a, 0, b + c);
		//0 -> 2
		dfs(0, b, a + c);
	}
}