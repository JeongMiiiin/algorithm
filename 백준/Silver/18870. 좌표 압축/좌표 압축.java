import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * 백준 18870 - 좌표 압축
 * 
 * 수직선 위에 N개의 좌표.
 * 좌표에 좌표압축을 적용하려 함
 * Xi를 좌표 압축한 결과 X'i의 값은 Xi > Xj를 만족하는 서로 다른 좌표의 개수와 같아야 함
 * 
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		int[] nList = new int[N];
		Set<Integer> set = new TreeSet<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < N; i++) {
			nList[i] = Integer.parseInt(st.nextToken());
			set.add(nList[i]);
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		int idx = 0;
		for(int n : set) map.put(n, idx++);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N; i++) sb.append(map.get(nList[i]) + " ");
		
		bw.write(sb.toString() + "\n");
		bw.close();
		
	}
}