import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		for(int i=0; i < N; i++) {
			int j=0;
			for(char c : br.readLine().toCharArray()) map[i][j++] = c;
		}
		StringBuilder sb = new StringBuilder();
		
		//결과 세기
		int result = 0;
		
		//가로 세기
		for(int i=0; i < N; i++) {
			int cnt = 0;
			for(int j=0; j < N; j++) {
				if(map[i][j] == '.') cnt++;
				else {
					if(cnt >= 2) result++;
					cnt = 0;
				}
			}
			
			//연속해서 2칸 이상이 있을 때
			if(cnt >= 2) result++;
		}
		
		sb.append(result + " ");
		
		result = 0;
		//세로 세기
		for(int i=0; i < N; i++) {
			int cnt = 0;
			for(int j=0; j < N; j++) {
				if(map[j][i] == '.') cnt++;
				else {
					if(cnt >= 2) result++;
					cnt = 0;
				}
			}
					
			//연속해서 2칸 이상이 있을 때
			if(cnt >= 2) result++;
		}	
		sb.append(result);
		
		System.out.println(sb.toString());
		br.close();
	}
}