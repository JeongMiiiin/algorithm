import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 2869번 달팽이는 올라가고 싶다
 * 받는 값
 * A : 낮에 올라갈 수 있는 높이
 * B : 밤에 내려오는 높이
 * V : 목표
*/
public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		int day = (V - B) / (A - B);
        
		// 나머지가 있을 경우
		if ((V - B) % (A - B) != 0) {
			day++;
		}
		System.out.println(day);
	}
}
