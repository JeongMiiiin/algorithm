import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 9019 - DSLR
 * 
 * 네 개의 명령어 D, S, L, R을 이용하는 계산기
 * 이 계산기에는 0 이상 10,000 미만의 십진수 저장 가능
 * 각 명령어는 이 레지스터에 저장된 n을 다음과 같이 변환
 * n의 네 자릿수를 d1, d2, d3, d4라고 하자
 * D : n을 두배로 증가 단, 결과값이 9999보다 큰 경우 10000으로 나눈 나머지
 * S : n에서 1을 뺀다. 단, 0에서 1을 빼면 9999로 변환
 * L : n의 각 자릿수를 왼편으로 회전하여 저장
 * R : n의 각 자릿수를 오른편으로 회전하여 저장
 * 
 * A를 B로 변환하기 위해 필요한 최소한의 명령어 나열을 출력. 명령어 나열이 여러가지면, 아무거나 출력
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static class Command {
		int n;
		String cmd;
		public Command(int n, String cmd) {
			this.n = n;
			this.cmd = cmd;
		}
		int DAction() {
			return (n * 2) % 10000;
		}
		
		int SAction() {
			return n == 0 ? 9999 : n - 1;
		}
		
		int LAction() {
	        return n % 1000 * 10 + n / 1000;
		}
		
		int RAction() {
	        return n % 10 * 1000 + n / 10;
		}
	}
	
	static int A, B, cnt;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t <= T; t++) {
			visited = new boolean[10000];
			st = new StringTokenizer(br.readLine(), " ");
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			visited[A] = true;
			
			Queue<Command> q = new ArrayDeque<>();
			q.add(new Command(A, ""));
			
			Command cur;
			while( !q.isEmpty() ) {
				cur = q.poll();
				
				if(cur.n == B) {
					bw.write(cur.cmd + "\n");
					break;
				}
				
				if(!visited[cur.DAction()]) {
					q.add(new Command(cur.DAction(), cur.cmd + "D"));
					visited[cur.DAction()] = true;
				}
				
				if(!visited[cur.SAction()]) {
					q.add(new Command(cur.SAction(), cur.cmd + "S"));
					visited[cur.SAction()] = true;
				}
				
				if(!visited[cur.LAction()]) {
					q.add(new Command(cur.LAction(), cur.cmd + "L"));
					visited[cur.LAction()] = true;
				}
				
				if(!visited[cur.RAction()]) {
					q.add(new Command(cur.RAction(), cur.cmd + "R"));
					visited[cur.RAction()] = true;
				}
			}
			
		}
		
		bw.close();
	}
	
}