import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * SWEA 5658 - 보물상자 비밀번호
 * N : 숫자의 개수 ( 4의 배수이며, 8이상 28이하의 정수)
 * 
 * 각 변에 16진수 숫자가 적혀 있는 보물상자
 * 보물상자의 뚜껑은 시계방향으로 회전, 한번 돌릴때마다 숫자가 시계방향으로 한 칸씩 회전
 * 각 변에는 동일한 개수의 숫자
 * 시계방향순으로 높은 자리 숫자에 해당하며 하나의 수를 나타냄
 * 보물상자에는 자물쇠가 걸려있는데, 이 자물쇠의 비밀번호는 보물 상자에 적힌 숫자로 만들 수 있는 모든 수 중,
 * K번째로 큰 수를 10진수로 만든 수이다.
 * N개의 숫자가 입력으로 주어질 떄, 보물상자의 비밀번호를 출력하는 프로그램 만들기
*/
public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String s = br.readLine();
			
			Set<Integer> hs = new TreeSet<>(); 
			Queue<Character> q = new ArrayDeque<>();
			String target;
			char[] ch;
			for(int i=0; i < N / 4; i++) {
				for(int j=0; j < 4; j++) {
					target = s.substring(j * (N / 4), j * (N / 4) + N / 4);
					hs.add(Integer.parseInt(target, 16));
				}
				ch = s.toCharArray();
				for(int k=0; k < N; k++) q.offer(ch[k]);
				s = "";
				for(int k=1; k < N; k++) s += q.poll();
				s = q.poll() + s;
			}
			
			int ans = 0;
			Iterator<Integer> ite = hs.iterator();
			
			int idx = 0;
			while(ite.hasNext() && idx++ <= hs.size() - K) ans = ite.next();
			
			//출력
			System.out.println("#" + t + " " + ans);
		}
	}
}
