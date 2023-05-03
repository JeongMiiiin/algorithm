import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 백준 20055 - 컨베이어 벨트 위의 로봇
 * 
 * 길이 N인 컨베이어벨트, 길이가 2N인 컨베이어 벨트를 위 아래로 감싸며 돌고 있음.
 * 벨트는 길이 1 간격으로 2N개의 칸으로 나뉘어져 있으며,
 * 
 * 각 칸은 1 ~ 2N까지 번호가 부여됨.
 * i번 칸의 내구도는 Ai.
 * 
 * 벨트는 한 칸 회전하면 1번부터 2N - 1번까지의 칸은 다음 번호로 이동
 * 2N번 칸은 1번 칸의 위치로 이동
 * 1번칸 -> 올리는 위치
 * N번칸 -> 내리는 위치
 * 
 * 컨베이어 벨트에 박스 모양 로봇을 하나씩 올리려 한다.
 * 로봇은 올리는 위치에만 올릴 수 있다.
 * 로봇은 내리는 위치에 도달하면 내린다.
 * 로봇은 컨베이어 벨트 위에서 스스로 이동.
 * 
 * 로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동하면
 * 그 칸의 내구도는 즉시 1만큼 감소
 * 컨베이어 벨트를 통해 로봇들을 건너편으로 옮기려고 한다.
 * 로봇을 옮기는 과정에서 아래와 같은 일이 순서대로 일어남
 * 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
 * 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동 가능하면 이동
 *    (로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아있어야 함
 * 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
 * 4. 내구도카 0인 칸의 개수가 K개 이상이라면 과정을 종료.
 * 
 * 종료되었을 때 몇번째 단계가 진행 중이었는지 출력 (가장 처음 수행되는 단계는 1번째)
*/
public class Main {
	static class Belt {
		int weight;
		boolean robot;
		public Belt(int weight, boolean robot) {
			this.weight = weight;
			this.robot = robot;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int step = 0;
		int zeroCnt = 0;
		
		Deque<Belt> conveyor = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i < N * 2; i++) conveyor.add(new Belt(Integer.parseInt(st.nextToken()), false));
		
		//찾기
		Belt cur;
		outer : while(zeroCnt < K) {
			step++;
			//한칸 회전
			conveyor.addFirst(conveyor.pollLast());

			//로봇 이동
			Stack<Belt> temp = new Stack<>();
			for(int i=0; i < N; i++) temp.add(conveyor.pollFirst());
			//N번째에 해당하는 칸 확인
			cur = temp.pop();
			if(cur.robot) cur.robot = false;
			conveyor.addFirst(cur);
			cur = temp.pop();
			if(cur.robot && conveyor.peekFirst().weight > 0) {
				if(--conveyor.peekFirst().weight == 0) {
					if(++zeroCnt >= K) break outer;
				}
				cur.robot = false;
			}
			conveyor.addFirst(cur);
			while(!temp.isEmpty()) {
				cur = temp.pop();
				if(cur.robot && conveyor.peekFirst().weight > 0 && !conveyor.peekFirst().robot) {
					if(--conveyor.peekFirst().weight == 0) {
						if(++zeroCnt >= K) break outer;
					}
					conveyor.peekFirst().robot = true;
					cur.robot = false;
				}
				conveyor.addFirst(cur);
			}
			cur = conveyor.pollFirst();
			if(cur.weight > 0) {
				cur.robot = true;
				if(--cur.weight == 0) {
					if(++zeroCnt >= K) break outer;
				}
			}
			conveyor.addFirst(cur);
		}
		
		//출력
		System.out.println(step);
		
	}
}