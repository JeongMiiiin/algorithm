import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			int n = Integer.parseInt(st.nextToken()), L = Integer.parseInt(st.nextToken()), F = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(sc.nextLine(), " ");
			String[] arr = new String[n];
			boolean[] visited = new boolean[n];
			for(int j=0; j < n; j++) arr[j] = st.nextToken();
			int result = 0;
			for(int j=0; j < n; j++) {
				if(visited[j]) continue;
				char[] target = arr[j].toCharArray();
				for(int z=j+1; z < n; z++) {
					if(visited[z]) continue;
					char[] compare = arr[z].toCharArray();
					int cnt = 0, idx = L - 1;
					while(cnt < F && idx > -1) {
						if(target[idx] != compare[idx]) break;
						idx--;
						cnt++;
					}
					if(cnt == F) {
						visited[j] = true;
						visited[z] = true;
						result++;
						break;
					}
					
				}
			}
			
			sb.append(result + "\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}