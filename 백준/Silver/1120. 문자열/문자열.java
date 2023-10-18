import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char[] compare1Str = sc.next().toCharArray();
		char[] compare2Str = sc.next().toCharArray();
		
		int result = compare1Str.length;
		
		//첫번째 글자가 더 짧거나 같을 때
		if(compare1Str.length <= compare2Str.length) {
			int idx = 0;
			while(idx <= compare2Str.length - compare1Str.length) {
				int cnt = compare1Str.length;
				for(int i=0, j=idx; j < compare1Str.length + idx; i++, j++) if(compare1Str[i] == compare2Str[j]) cnt--;
				result = Math.min(result, cnt);
				idx++;
			}
		} else {
			int idx = 0;
			while(idx <= compare1Str.length - compare2Str.length) {
				int cnt = compare2Str.length;
				for(int i=0, j=idx; j < compare2Str.length + idx; i++, j++) if(compare1Str[j] == compare2Str[i]) cnt--;
				result = Math.min(result, cnt);
				idx++;
			}
		}
		
		System.out.println(result);
		sc.close();
	}
}