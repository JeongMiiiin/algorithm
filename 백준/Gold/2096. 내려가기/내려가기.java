import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 2096 - 내려가기
 * N줄에 0 ~ 9 의 숫자가 3개씩 적혀 있음.
 * 내려가기 게임 진행중
 * 첫줄에서 시작하여 마지막 줄에서 끝나게 됨
 * 처음에 적혀있는 3개의 숫자 중에서 하나를 골라서 시작
 * 다음 줄로 내려갈 때 바로 아래의 수로 넘어가거나 아래의 수와 붙어있는 수로만 이동 가능
 * 얻을 수 있는 최대 점수와 최소 점수를 출력하라
 * 
 * 각 위치에 최대와 최소를 저장해두고 계속 갱신
*/
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] max = new int[3];
		int[] min = new int[3];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n0 = Integer.parseInt(st.nextToken());
		int n1 = Integer.parseInt(st.nextToken());
		int n2 = Integer.parseInt(st.nextToken());
		max[0] = min[0] = n0;
		max[1] = min[1] = n1;
		max[2] = min[2] = n2;
		for(int i=1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			n0 = Integer.parseInt(st.nextToken());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			int beforeMax0 = max[0], beforeMax2 = max[2];
			max[0] = Math.max(max[0], max[1]) + n0;
			max[2] = Math.max(max[1], max[2]) + n2;
			max[1] = Math.max(Math.max(beforeMax0, max[1]), beforeMax2) + n1;
			
			int beforeMin0 = min[0], beforeMin2 = min[2];
			min[0] = Math.min(min[0], min[1]) + n0;
			min[2] = Math.min(min[1], min[2]) + n2;
			min[1] = Math.min(Math.min(beforeMin0, min[1]), beforeMin2) + n1;
			
		}
		
		int answerMax = Math.max(Math.max(max[0], max[1]), max[2]);
		int answerMin = Math.min(Math.min(min[0], min[1]), min[2]);
		
		System.out.println(answerMax + " " + answerMin);
	}
	
}