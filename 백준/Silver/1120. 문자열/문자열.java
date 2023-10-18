import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char[] compare1Str = sc.next().toCharArray();
		char[] compare2Str = sc.next().toCharArray();
		
		char[] compare1, compare2;
		int result = compare1Str.length;
		if(compare1Str.length <= compare2Str.length) {
			compare1 = Arrays.copyOf(compare1Str, compare1Str.length);
			compare2 = Arrays.copyOf(compare2Str, compare2Str.length);
		} else {
			compare1 = Arrays.copyOf(compare2Str, compare2Str.length);
			compare2 = Arrays.copyOf(compare1Str, compare1Str.length);
		}
		
		int idx = 0;
		while(idx <= compare2.length - compare1.length) {
			int cnt = compare1.length;
			for(int i=0, j=idx; j < compare1.length + idx; i++, j++) if(compare1[i] == compare2[j]) cnt--;
			result = Math.min(result, cnt);
			idx++;
		}
		
		System.out.println(result);
		sc.close();
	}
}