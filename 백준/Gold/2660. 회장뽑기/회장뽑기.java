import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 2660 - 회장뽑기
 * 각 회원은 다른 회원들과 가까운 정도에 따라 점수를 받게 됨.
 * 어느 회원이 다른 모든 회원과 친구라면 1점
 * 중간에 아는 사람 수가 끼는 만큼 1 + 중간 사람 점수
 * 회장은 회원들 중에서 점수가 가장 작은 사람이 된다.
 * 회장의 점수와 회장이 될 수 있는 모든 사람을 찾아라.
 * 입력 :
 * 첫 째줄 : 회원의 수
 * 둘 째줄 이후 : 친구인 두개의 회원 번호
 * -1이 나오면 마지막 줄로 입력 종료
 * 출력 :
 * 첫 째줄 : 회장 후보의 점수와 후보의 수
 * 둘 째줄 : 회장 후보들을 오름차순으로 모두 출력
 * 
 * 한명씩 보내보면서 점수가 낮아지면 다시 초기화하는 방향
 * */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 1][N + 1];
		StringTokenizer st;
		//맵 세팅
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(start == -1) break;
			map[start][end] = 1;
			map[end][start] = 1;
		}
		
		int result = Integer.MAX_VALUE;
		List<Integer> resultList = new ArrayList<>();
		
		Queue<Integer> q = new LinkedList<>();
		int cnt = 0;
		boolean[] visited = new boolean[N + 1];
		//찾기
		for(int i=1; i <= N; i++) {
			q.add(i);
			visited[i] = true;
			while(!q.isEmpty()) {
				int size = q.size();
				for(int j=0; j < size; j++) {
					int cur = q.poll();
					for(int z=1; z <= N; z++) {
						if(map[cur][z] == 1 && !visited[z]) {
							q.add(z);
							visited[z] = true;
						}
					}
				}
				
				cnt++;
			}
			
			cnt--;
			
			if(cnt < result) {
				result = cnt;
				resultList.clear();
				resultList.add(i);
			} else if(cnt == result) resultList.add(i);
			
			cnt = 0;
			Arrays.fill(visited, false);
		}
		
		
		Collections.sort(resultList);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < resultList.size() - 1; i++) sb.append(resultList.get(i) + " ");
		sb.append(resultList.get(resultList.size() - 1));
		
		System.out.println(result + " " + resultList.size());
		System.out.println(sb.toString());
		
		br.close();
	}
}