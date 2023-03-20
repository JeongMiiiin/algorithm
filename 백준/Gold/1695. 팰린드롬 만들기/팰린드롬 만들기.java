import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 1695 - 팰린드롬 만들기
 * 
 * 주어지는 값
 * N : 수열의 길이
 * 두번째 줄 : 수열의 숫자 순서
 * 
 * 앞에서 뒤로 보나, 뒤에서 앞으로 보나 같은 수열을 팰린드롬 이라고 한다.
 * 
 * 한 수열이 주어졌을 때, 이 수열에 최소 개수의 수를 끼워 넣어 팰린드롬을 만들려고 한다.
 * 최소 몇 개의 수를 끼워 넣으면 되는지를 알아내는 프로그램 작성
 *
 * DP...
 * 이분 탐색으로 같은지 확인?
 * 
 * 단, 그 개수가 N / 2와 같을 때는 1개?
 * 
 * N이 짝수이고, 다른 값이 N / 2 - 1 => 2개
 * 
 * 답 : min(시작 숫자를 시작으로 하는 팰린드롬을 만드는 데 필요한 개수, 마지막 숫자를 끝으로 하는 팰린드롬을 만드는 데 필요한 개수) + 안 쪽의 수열을 팰린드롬으로 만들기 위한 개수
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N;
	static int[] NList;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		NList = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i < N; i++) NList[i] = Integer.parseInt(st.nextToken());
		
		dp = new int[N][N];
		
		for(int i=0; i < N; i++) Arrays.fill(dp[i], -1);
		
		System.out.println(find(0, N - 1));
	}
	private static int find(int start, int end) {
		if(dp[start][end] != -1) return dp[start][end]; //이미 해당 조합 수를 지났을 때
		if(start == end) return 0; //가운데서 만났을 때
		else if(start + 1 == end) { //옆에 위치하고 있을 때
			if(NList[start] == NList[end]) return 0; // 둘이 같은 숫자면 팰린드롬이 이미 완성
			return 1; //다른 숫자면 팰린드롬을 위해 하나가 필요함
		}
		
		if(NList[start] == NList[end]) return dp[start][end] = find(start + 1, end - 1); // 둘이 같은 숫자면 한칸씩 땡기기
		else return dp[start][end] = Math.min(find(start,  end - 1), find(start + 1, end)) + 1; //둘이 다른 숫자면 한쪽으로 땡겼을 때 가장 최소의 개수에 1 더하기
	}
}