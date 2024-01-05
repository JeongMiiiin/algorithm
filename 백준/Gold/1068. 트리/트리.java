import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList[N];
		for(int i=0; i < N; i++) list[i] = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < N; i++) { //트리 세팅
			int parent = Integer.parseInt(st.nextToken());
			if(parent > -1) list[parent].add(i);
		}
		
		int removeNum = Integer.parseInt(br.readLine());
		
		treeRemove(removeNum);
		
		int result = 0;
		//리프노드 세기
		for(int i=0; i < N; i++) {
			if(list[i] == null) continue; //삭제처리된 노드면 패스
			
			boolean flag = true;
			for(int j : list[i]) {
				if(list[j] != null) { //자식이 살아 있으면
					flag = false;
					break;
				}
			}
			
			if(flag) result++;
		}
		
		System.out.println(result);
		br.close();
	}

	private static void treeRemove(int removeNum) {
		for(int child : list[removeNum]) treeRemove(child); //자식들 삭제 처리
		list[removeNum] = null; //삭제
	}
}