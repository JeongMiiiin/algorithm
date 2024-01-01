import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			int result = -1;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			double firstSpeed = 0;
			st = new StringTokenizer(br.readLine(), " ");
			//가장 빠른 속도 구하기
			for(int i=0; i < N - 1; i++) firstSpeed = Math.max(Integer.parseInt(st.nextToken()), firstSpeed);
			
			double mySpeed = Integer.parseInt(st.nextToken());
			
			if(firstSpeed < mySpeed) result = 0; //이미 순위가 1위일 경우
			else {
				double firstArrival = X / firstSpeed;
				int remain = X - Y;
				double maxArrival = remain > 0 ? remain / mySpeed + 1 : 1;
				if(firstArrival > maxArrival) { //부스터로 뒤집을 수 있는 경우
					int left = 0, right = Y, mid;
					while(left < right) {
						mid = (left + right) / 2;
						remain = X - mid;
						maxArrival = remain > 0 ? remain / mySpeed + 1 : 1;
						if(firstArrival > maxArrival) { //뒤집을 수 있을 때
							result = mid;
							right = mid;
						} else left = mid + 1;
					}
				}
			}
				
			sb.append(result + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}