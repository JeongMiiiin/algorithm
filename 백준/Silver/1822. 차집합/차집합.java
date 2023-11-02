import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 백준 1822 - 차집합
 * 몇 개의 자연수로 이루어진 두 집합 A, B가 있다.
 * 집합 A에는 속하면서 B에는 속하지 않는 모든 원소를 구하라
 * */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int Acnt = Integer.parseInt(st.nextToken());
		int Bcnt = Integer.parseInt(st.nextToken());
		Map<Integer, Boolean> A = new HashMap<>();
		Map<Integer, Boolean> B = new HashMap<>();
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < Acnt; i++) A.put(Integer.parseInt(st.nextToken()), true);
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < Bcnt; i++) B.put(Integer.parseInt(st.nextToken()), true);
		
		int result = 0;
		List<Integer> list = new ArrayList<>();
		for(Map.Entry<Integer, Boolean> entry : A.entrySet()) {
			if(B.get(entry.getKey()) == null) {
				result++;
				list.add(entry.getKey());
			}
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < list.size(); i++) sb.append(list.get(i) + " ");
		
		System.out.println(result);
		if(list.size() > 0) System.out.println(sb.toString());
		br.close();
	}
}