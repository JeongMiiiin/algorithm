import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //도시 개수
		int M = sc.nextInt(); //도로 개수
		int K = sc.nextInt(); //거리 정보
		int X = sc.nextInt(); //출발 도시
		//인접리스트 세팅
		ArrayList<Integer>[] map = new ArrayList[N + 1];
		for(int i=1; i <= N; i++) map[i] = new ArrayList<>();
		for(int i=0; i < M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			map[start].add(end);
		}
		boolean[] isVisited = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();
		isVisited[X] = true;
		q.add(X);
		int idx = 0, size, cur;
		//비어있고 거리가 도달하지 않았을 때
		while(!q.isEmpty() && idx++ < K) {
			size = q.size();
			for(int i=0; i < size; i++) {
				cur = q.poll();
				for(int j : map[cur]) {
					if(!isVisited[j]) { //아직 방문하지 않은 곳이라면
						isVisited[j] = true;
						q.add(j);
					}
				}
			}
		}
		//비어있을 때 -1 추가
		if(q.isEmpty()) q.add(-1);
		int[] arr = new int[q.size()];
		idx = 0;
		while(!q.isEmpty()) arr[idx++] = q.poll();
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < arr.length; i++) sb.append(arr[i] + "\n");
		System.out.println(sb.toString());
		sc.close();
	}
}