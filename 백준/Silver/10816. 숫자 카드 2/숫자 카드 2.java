import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 10816번 숫자 카드 2
 * 주어지는 값
 * N : 숫자 카드의 개수
 * 둘째 줄 : 카드들의 수
 * M : 몇개인지 찾아야 하는 정수의 개수
 * 넷째 줄 : 정수들의 수
*/
public class Main {
	static int[] inputs;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		inputs = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i < N; i++)
			inputs[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(inputs);
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < M; i++) {
			int target = Integer.parseInt(st.nextToken());
			sb.append((upperBound(target) - lowerBound(target)) + " ");
		}
			
		
		System.out.println(sb.toString());
	}
	
	private static int lowerBound(int key) {
		int lo = 0; 
		int hi = inputs.length; 
 
		// lo가 hi랑 같아질 때 까지 반복
		while (lo < hi) {
 
			int mid = (lo + hi) / 2; // 중간위치를 구한다.
 
			//key 값이 중간 위치의 값보다 작거나 같을 경우
			if (key <= inputs[mid]) hi = mid;
			else lo = mid + 1;
			
 
		}
 
		return lo;
	}
 
	private static int upperBound(int key) {
		int lo = 0; 
		int hi = inputs.length; 
 
		// lo가 hi랑 같아질 때 까지 반복
		while (lo < hi) {
 
			int mid = (lo + hi) / 2; // 중간위치를 구한다.
			// key값이 중간 위치의 값보다 작을 경우
			if (key < inputs[mid]) hi = mid;
			// 중복원소의 경우 else에서 처리된다.
			else lo = mid + 1;

		}
 
		return lo;
	}
	
}
