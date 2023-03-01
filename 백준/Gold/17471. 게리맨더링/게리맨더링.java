import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/*
 * 백준 17471 - 게리맨더링
 * 주어지는 값
 * N : 구역(정점)의 개수
 * 둘째 줄 : 각 구역의 인구 수
 * 세번째 줄부터 N개의 줄 : 각 구역과 인접한 구역의 정보
 * 정보
 * 1. 해당 구역과 인접한 구역의 수
 * 2. 인접한 구역들의 번호
 * 선거구의 조건
 * 1. 구역을 적어도 1개 이상 포함
 * 2. 모두 연결되어 있어야 함
 * 출력값
 * 두 선거구의 인구 차이의 최솟값 출력하라 ( 단, 나눌 수 없을 때에는 -1 출력)
 * 1. 각 정점들을 관리하는 Vertex class 생성 (구역 번호, 인구 수, 인접 구역 번호)
 * 2. 부분집합으로 두개의 선거구로 나눌 수 있는 부분집합 생성
 * 3. 실제로 연결 가능한지 확인
 * 4. 연결이 가능하면 값 비교 후에 최소값 갱신
*/
public class Main {
	static class Vertex {
		int no;
		int people;
		int[] linkList;
		public Vertex(int no, int people) {
			this.no = no;
			this.people = people;
		}
		
	}
	static int N, ans = Integer.MAX_VALUE;
	static Vertex[] areas;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		areas = new Vertex[N];
		//인구수 정보를 담아서 구역 생성
		for(int i=0; i < N; i++) areas[i] = new Vertex(i, sc.nextInt());
		
		//인접 구역들 추가
		for(int i=0; i < N; i++) {
			int adjCnt = sc.nextInt();
			int[] adjList = new int[adjCnt];
			for(int j=0; j < adjCnt; j++) adjList[j] = sc.nextInt() - 1;
			areas[i].linkList = adjList;
		}
		
		subset();
		
		if(ans == Integer.MAX_VALUE) ans = -1;
		
		System.out.println(ans);
		
		sc.close();
	}
	
	private static void subset() {
		for(int i=0; i < 1 << N; i++) {
			List<Integer> list1 = new ArrayList<Integer>();
			List<Integer> list2 = new ArrayList<Integer>();
			for(int j=0; j < N; j++) {
				if((i & 1 << j) != 0) list1.add(j);
				else list2.add(j);
			}
			
			if(list1.size() == 0 || list2.size() == 0) continue;
			
			if(canGo(list1) && canGo(list2)) {
				int diff = Math.abs(peopleSum(list1) - peopleSum(list2));
				ans = Math.min(ans, diff);
			}
			
		}
		
	}
	
	//실제로 갈 수 있는지 여부를 판단하는 함수
	private static boolean canGo(List<Integer> list) {
		boolean[] selected = new boolean[N]; //선택된 값을 표시하기 위한 boolean 배열
		boolean[] visited = new boolean[N]; //방문 여부를 표시하기 위한 boolean 배열
		
		int cnt = 1;
		
		for(int n : list) selected[n] = true;
		
		Queue<Integer> q = new ArrayDeque<>();
		int n = list.get(0);
		visited[n] = true;
		q.offer(n);
		
		outer : while( !q.isEmpty() ) {
			int cur = q.poll();
			Vertex v = areas[cur];
			int[] adj = v.linkList;
			for(int i=0; i < adj.length; i++) {
				int n1 = adj[i];
				if(selected[n1]) {
					if(!visited[n1]) {
						visited[n1]= true;
						if(++cnt == list.size()) break outer;
						q.offer(n1);
					}
				}
			}
		}
		
		if(cnt == list.size()) return true;
		return false;
	}
	
	//인구수를 종합하여 리턴하는 함수
	private static int peopleSum(List<Integer> list) {
		int result = 0;
		for(int n : list) result += areas[n].people;
		return result;
	}
	
}
