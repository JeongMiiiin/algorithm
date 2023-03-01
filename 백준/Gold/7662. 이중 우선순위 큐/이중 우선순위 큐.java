import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * 백준 7662번 이중 우선순위 큐
 * 주어지는 값
 * T : 테스트 데이터 개수
 * k : 큐에 적용할 연산의 개수
 * k줄
 * 1. 연산을 나타내는 문자 (D OR I)
 * 2. 정수 n
 * D 1 -> 최댓값 삭제
 * D -1 -> 최솟값 삭제
 * 큐가 비어있을 때 D는 무시
 * 출력값
 * 모든 연산 처리 후 큐에 남아있는 최댓값과 최솟값 도출
 * 비어있을 때는 EMPTY로 출력
*/
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t < T; t++) {
			//입력
			int k = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> treeMap = new TreeMap<>();

			StringTokenizer st;
			//처리
			for(int i=0; i < k; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String cmd = st.nextToken(); //명령어 종류
				int n = Integer.parseInt(st.nextToken());
				if(cmd.equals("D")) { //꺼내야할 때
					if(treeMap.isEmpty()) continue;
					if(n == 1) {
						int maxKey = treeMap.lastKey();
						if(treeMap.get(maxKey) == 1) treeMap.remove(maxKey);
						else treeMap.put(maxKey, treeMap.get(maxKey) - 1);
					} else {
						int minKey = treeMap.firstKey();
						if(treeMap.get(minKey) == 1) treeMap.remove(minKey);
						else treeMap.put(minKey, treeMap.get(minKey) - 1);
					}
				} else treeMap.put(n, treeMap.getOrDefault(n, 0) + 1);
			}
			
			if( !treeMap.isEmpty() ) {
				System.out.println(treeMap.lastKey() + " " + treeMap.firstKey());
			} else System.out.println("EMPTY");
			
		}
		
		
	}
}
