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
		for (int i = -list2.size(); i <= list1.size(); i++) {
			boolean isPossible = true;
			for (int j = 0; j < list1.size(); j++) {
				isPossible &= ((j - i < 0 || j - i >= list2.size()) ? 0 : list2.get(j - i)) + list1.get(j) <= 3;
			}
			if (isPossible) {
				result = Math.min(result, Math.max(i + list2.size(), list1.size()) - Math.min(i, 0));
			}
		}
		
		System.out.println(result);
		br.close();
	}
}