import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * SWEA 1238 - Contact
 * 주어지는 값
 * 테스트 케이스는 10개
 * 연락 인원은 최대 100명
 * 부여될 수 있는 번호는 1 ~ 100
 * 각 테스트 케이스 첫번째 줄 : 입력 받는 데이터의 길이, 시작 당번 번호
 * 비상연락막과 연락을 시작하는 당번에 대한 정보가 주어짐.
 * 양방향 통신
 * 다자 통화 가능
 * 마지막에 통화한 사람 중 가장 큰 번호를 출력하라
 * 1. 각 정점을 관리하는 Vertex class 생성 (자신의 번호, 연락가능한 정점을 관리하기 위한 리스트)
 * 2. 어떤 번호가 사용될지 모르기에 전체 정점을 담기 위한 Vertex 배열을 생성
 * 3. 데이터를 받아서 해당 값이 위치할 곳이 비어있지 않으면 연락 가능한 망을 추가해주고, 만들어야 한다면 만들어서 추가
 * 4. 시작점부터 출발하여 끝까지 연락망을 돌리기 (Queue 사용해보기)
 * 5. 
*/
public class Solution {
	static class Vertex {
		int no;
		Set<Integer> linkList;

		public Vertex(int no) {
			this.no = no;
			this.linkList = new HashSet<Integer>();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		for(int t=1; t <= 10; t++) {
			Vertex[] all = new Vertex[101];
			boolean[] visited = new boolean[101];
			
			st = new StringTokenizer(br.readLine(), " ");
			int len = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i < len / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				if(all[from] == null) all[from] = new Vertex(from);
				if(all[to] == null) all[to] = new Vertex(to);
				all[from].linkList.add(to);
			}
			
			Queue<Integer> q = new ArrayDeque<Integer>();
			q.offer(start);
			visited[start] = true;
			
			int ans = 0;
			while(!q.isEmpty()) {
				int size = q.size();
				int temp = 0;
				for(int i=0; i < size; i++) {
					int cur = q.poll();
					temp = Math.max(temp, cur);
					Vertex v = all[cur];
					Iterator<Integer> canCall = v.linkList.iterator();
					while(canCall.hasNext()) {
						int n = canCall.next();
						if( !visited[n] ) {
							
							visited[n] = true;
							q.offer(n);
						}
					}
				}
				ans = temp;
			}
			
			
			
			//출력
			bw.write("#" + t + " " + ans + "\n");
		}
		
		bw.close();
	}
}
