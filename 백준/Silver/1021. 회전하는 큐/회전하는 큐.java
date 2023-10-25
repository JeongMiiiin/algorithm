import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i=1; i <= N; i++) dq.add(i);
		int M = Integer.parseInt(st.nextToken());
		int[] numList = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < M; i++) numList[i] = Integer.parseInt(st.nextToken());
		
		int result = 0, idx = 0, target, size, cnt;
		while(idx < M) {
			target = numList[idx++];
			size = dq.size();
			cnt = 0;
			while(dq.peekFirst() != target) {
				dq.add(dq.pollFirst());
				cnt++;
			}
			dq.pollFirst();
			if(size / 2 < cnt) cnt = size - cnt;
			result += cnt;
		}
		
		System.out.println(result);
		br.close();
	}
}