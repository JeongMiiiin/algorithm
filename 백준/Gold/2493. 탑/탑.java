import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 2493번 탑
 * 스택을 이용해 출력을 많이 함
 * 2차원 배열로 값 관리 (value, 이전에 나보다 value가 큰 인덱스) 
*/
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] data = new int[N + 1];
		int[] D = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i <= N; i++) data[i] = Integer.parseInt(st.nextToken());
		
		for(int i=2; i <= N; i++) {
			int j = i - 1; //초기 비교위치
			while(j > 0) {
				if(data[i] < data[j]) {
					D[i] = j;
					break;
				}
				j = D[j];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i <=N; i++) sb.append(D[i] + " ");
		System.out.println(sb.toString());
	}
}
