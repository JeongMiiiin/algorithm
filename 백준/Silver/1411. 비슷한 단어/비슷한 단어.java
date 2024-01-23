import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		List<Integer>[][] map = new ArrayList[N][26];
		for(int i=0; i < N; i++) {
			int j = 0;
			for(char c : sc.nextLine().toCharArray()) {
				if(map[i][c - 'a'] == null) map[i][c - 'a'] = new ArrayList<>();
				map[i][c - 'a'].add(j++);
			}
		}
		int result = 0;
		for(int i = 0; i < N - 1; i++) {
			for(int j = i + 1; j < N; j++) {
				boolean[] visited = new boolean[26]; //방문처리 배열
				boolean temp = true;
				for(int n1=0; n1 < 26; n1++) {
					if(map[i][n1] != null) {
						boolean temp2 = false;
						for(int n2=0; n2 < 26; n2++) {
							if(visited[n2] || map[j][n2] == null || map[i][n1].size() != map[j][n2].size()) continue; //이미 방문했던 곳이거나 개수가 틀린 경우 패스
							int n2Idx = 0;
							boolean temp3 = true;
							for(int n1Num : map[i][n1]) {
								if(n1Num != map[j][n2].get(n2Idx++)) {
									temp3 = false;
									break;
								}
							}
							if(temp3) {
								temp2 = true;
								visited[n2] = true;
								break;
							}
						}
						if(!temp2) {
							temp = false;
							break;
						}
					}
				}
				if(temp) result++;
			}
		}
		
		System.out.println(result);
		sc.close();
	}
}