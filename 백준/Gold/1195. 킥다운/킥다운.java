import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> list1 = new ArrayList<>();
		for(char c : br.readLine().toCharArray()) list1.add(c - '0');
		List<Integer> list2 = new ArrayList<>();
		for(char c : br.readLine().toCharArray()) list2.add(c - '0');
		
		int result = list1.size() + list2.size();
		//안겹치게 시작
		for (int i = -list2.size(); i <= list1.size(); i++) {
			boolean isPossible = true; //되는지 체크해주는 변수
			
			//바깥으로 삐져나와있을 때는 0으로 처리, 겹칠 경우 list2의 값을 갖고 더한 값이 3 초과면 false로 처리
			for (int j = 0; j < list1.size(); j++) isPossible &= ((j - i < 0 || j - i >= list2.size()) ? 0 : list2.get(j - i)) + list1.get(j) <= 3;
			
			//가능한 조합이면 result 값 최소로 갱신
			if (isPossible) result = Math.min(result, Math.max(i + list2.size(), list1.size()) - Math.min(i, 0));
		}
		
		System.out.println(result);
		br.close();
	}
}