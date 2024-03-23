import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int result = 0;
		for(int i=1; i <= N; i++) if(check(i)) result++;
		
		System.out.println(result);
		sc.close();
	}
	
	private static boolean check(int target) {
		boolean result = true;
		String s = Integer.toString(target);
		int[] arr = new int[s.length()];
		if(arr.length > 1) {
			int idx = 0;
			for(char c : s.toCharArray()) arr[idx++] = c - '0';
			
			int diff = arr[1] - arr[0];
			int init = arr[0] - diff;
			for(int next : arr) {
				if(init + diff != next) {
					result = false;
					break;
				} else init += diff;
			}
		}
		
		return result;
	}
}