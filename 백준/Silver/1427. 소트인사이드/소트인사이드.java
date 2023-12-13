import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String target = br.readLine();
		Integer[] arr = new Integer[target.length()];
		int index = 0;
		for(char c : target.toCharArray()) arr[index++] = Integer.parseInt(Character.toString(c));
		Arrays.sort(arr, Collections.reverseOrder());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < arr.length; i++) sb.append(arr[i]);
		
		System.out.println(sb.toString());
		br.close();
	}
}