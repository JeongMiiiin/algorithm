import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int idx = 0; //현재 인덱스 값
		int result = 0; //결과 값
		int cur = 0; //처음 출발하는 값
		boolean[] isVisited = new boolean[N + 1];
		for(int i=2; i <= N; i++) {
			if(isVisited[i] || !check(i)) continue; //방문했던 숫자거나 소수가 아닌 경우 패스
			
			cur = i;
			//도착했다면
			if(++idx == K) {
				result = cur;
				break;
			}
			
			while(idx < K && cur <= N - i) {
				cur += i;
				if(isVisited[cur]) continue; //방문한 숫자면 패스
				idx++;
				isVisited[cur] = true; //방문 처리
			}
			
			//도착했을 때
			if(idx == K) {
				result = cur;
				break;
			}
		}
		
		System.out.println(result);
		sc.close();
	}
	
	private static boolean check(int n) {
		boolean result = true;
		for(int i=2; i < Math.sqrt(n); i++) {
			if(n % i == 0) { //소수가 아닐 때
				result = false;
				break;
			}
		}
		
		return result;
	}
}