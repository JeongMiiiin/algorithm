import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 6064 - 카잉 달력
 * 
 * 카잉 제국 백성들은 특이한 달력을 사용
 * 그들은 M과 N보다 작거나 같은 두 개의 자연수 x, y를 가지고 각 년도를 <x:y>와 같은 형식으로 표현
 * 최초 : <1:1>
 * 2번째 : <2:2>
 * <x:y>의 다음해는 <x':y'>인데
 * 여기서 x < M이면 x' = x + 1이고, 아니면 x' = 1;
 * 여기서 y < N이면 y' = y + 1이고, 아니면 y' = 1;
 * 네 개의 정 수 M, N, x와 y가 주어질 때, <M:N>이 카잉 달력의 마지막 해라고 하면 <x:y>는 몇번째 해를 나타내는지 구하는 프로그램 작성
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			int ans = 0;
			boolean flag = false;
			for (int i = x; i < (N * M); i += M) {
				if (i % N == y) {
					ans = i + 1;
					flag = true;
					break;
				}
			}

			if (!flag) ans = -1;
			
			bw.write(ans + "\n");
		}
		bw.close();
	}
}