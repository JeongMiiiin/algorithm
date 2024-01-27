import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int J = sc.nextInt();
		int left = 1, right = M, result = 0;
		for(int i=0; i < J; i++) {
			int target = sc.nextInt();
			if(left <= target && right >= target) continue; //이미 반경에 있을 때
			int leftDiff = left - target;
			int rightDiff = target - right;
			while(leftDiff-- > 0) {
				left--;
				right--;
				result++;
			}
			while(rightDiff-- > 0) {
				left++;
				right++;
				result++;
			}
		}
		
		System.out.println(result);
		sc.close();
	}
}