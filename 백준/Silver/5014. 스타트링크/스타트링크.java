import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 5014번 - 스타트링크
 * F층 사무실이 있음.
 * G층에서 s층으로 가는 최소값 구하기
 * U버튼으로 위로 U층을 가거나
 * D버튼으로 아래로 D층 이동 가능
 * (단, 해당하는 층이 없으면 움직이지 않음)
 * 만약 엘리베이터로 못 갈시 use the stairs 출력
 * 
 * Queue로 BFS를 구현해보자
 * */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int F = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int U = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int resultCnt = -1, cnt = 0;
		boolean[] visited = new boolean[F + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(S);
		visited[S] = true;
		outer : while(!q.isEmpty()){
			int size = q.size();
			for(int i=0; i < size; i++) {
				int cur = q.poll();
				if(cur == G) {
					resultCnt = cnt;
					break outer;
				}
				
				if(cur + U <= F && !visited[cur + U]) {
					visited[cur + U] = true;
					q.add(cur + U);
				}
				
				if(cur - D >= 1 && !visited[cur - D]) {
					visited[cur - D] = true;
					q.add(cur - D);
				}
			}
			cnt++;
		}
		
		String result = String.valueOf(resultCnt);
		if(resultCnt == -1) result = "use the stairs";
		bw.write(result);
		
		bw.close();
		br.close();
	}
}