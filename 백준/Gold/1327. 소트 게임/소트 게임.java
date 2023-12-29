import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 백준 1327 - 소트 게임
 * 1부터 N까지 정수로 이루어진 N자리의 순열을 이용
 * 게임에선 K가 주어짐.
 * 어떤 수를 뒤집으면, 그 수부터 오른쪽으로 K개의 수를 뒤집어야 함.
 * */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		char[] arr = new char[N];
		arr = br.readLine().replace(" ", "").toCharArray();
		char[] temp = Arrays.copyOf(arr, N);
		Arrays.sort(arr);
		String correct = new String(arr);
		int result = 0;
		
		Set<String> set = new HashSet<>();
		Queue<String> q = new LinkedList<>();
		String init = new String(temp); 
		q.add(init);
		
		outer : while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i < size; i++) {
				String cur = q.poll();
				if(cur.equals(correct)) {
					set.add(cur);
					break outer;
				} else if(!set.contains(cur)) {
					set.add(cur);
					for(int j=0; j <= N - K; j++) q.add(changePos(cur, j, K));
				}
			}
			
			result++;
		}
		
		System.out.println(set.contains(correct) ? result : -1);
		br.close();
	}
	
	private static String changePos(String origin, int start, int K) {
		StringBuilder sb = new StringBuilder();
		sb.append(origin.substring(0, start));
		char[] target = origin.substring(start, start + K).toCharArray();
		for(int i= target.length - 1; i >= 0; i--) sb.append(target[i]);
		sb.append(origin.substring(start + K, origin.length()));
		return sb.toString();
	}
}