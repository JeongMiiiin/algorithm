import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] cList = br.readLine().toCharArray();
		
		PriorityQueue<String> pq = new PriorityQueue<>();
		int len = cList.length;
		StringBuilder sb = new StringBuilder();
		for(int i=1; i <= len - 2; i++) {
			for(int j=1; j <= len - 2; j++) {
				for(int z=1; z <= len - 2; z++) {
					if(i + j + z != len) continue; //합쳐서 벗어나면 패스
					//초기화
					sb.setLength(0);
					//뒤집어서 넣기
					for(int ni = i - 1; ni >= 0; ni--) sb.append(cList[ni]);
					for(int nj = j + i - 1; nj >= i; nj--) sb.append(cList[nj]);
					for(int nz = j + i + z - 1; nz >= j + i; nz--) sb.append(cList[nz]);
					pq.add(sb.toString());
				}
			}
		}
		System.out.println(pq.poll());
		br.close();
		
	}
}