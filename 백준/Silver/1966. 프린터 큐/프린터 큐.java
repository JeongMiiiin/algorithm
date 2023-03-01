import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 프린터 큐
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	private static class Print {
		public int important;
		public int order;
		public Print(int important, int order) {
			this.important = important;
			this.order = order;
		}
	}
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			Queue<Print> q = new ArrayDeque<Print>();
			st = new StringTokenizer(br.readLine());
			int max = -1;
			int[] fre = new int[10];
			for(int i=0; i < N; i++) {
				int n = Integer.parseInt(st.nextToken());
				q.add(new Print(n, i));
				fre[n]++;
				max = Math.max(max, n);
			}
				
			int ans = 0;
			int pos = M;
			outer : while(q.size() > 0) {
				Print n = q.poll();
				pos--;
				if(n.important < max) {
					q.add(n);
				} else {
					ans++;
					fre[n.important]--;
					if(n.order == M) break outer;
					if(fre[n.important] == 0) {
						for(int i= n.important -1; i > 0; i--) {
							if(fre[i] > 0) {
								max = i;
								break;
							}
						}
					}
				}
			}
			
			System.out.println(ans);
			
		}
	}
}
