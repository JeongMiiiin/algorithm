import java.util.Scanner;

/*
 * 백준 2011 - 암호코드
 * 
 * 상근이와 선영이의 대화를 서로 암호화하기로 했음.
 * 
 * A - 1, B - 2 ... 등으로 바꾸는데
 * 암호가 주어졌을 때 몇가지로 해석이 가능한지 경우의 수를 구하라
 * 
 * 
 * 25114
 * 2 5 1 1 4
 * 25 1 1 4
 * dp[0] => 2, 2, 2, 25, 25, 25 -- 2, 25
 * dp[1] => 5, 5, 5, 1,  1,  11 -- 5, 1, 11
 * dp[2] => 1, 1, 11, 1, 14, 4  -- 1, 11, 14, 4
 * dp[3] => 1, 14, 4, 4         -- 1, 14, 4
 * dp[4] => 4                   -- 4
 * 
 * 
 * 순열?
 * N길이의 문자열을 자를 수 있는 방법 
 * N번으로 -> (N - i + 1)(n - i + 1)개
 * N - 1 -> 
 * 
 * 뒤에 n길이가 남았을 때 1개면 1개
 * 두개면 3개
 * 3개면 3개
 * 4개면
 * 
 * 완탐을 구성하고 저장할 것을 찾아보자
 * 
*/
public class Main {
	
	static int ans, len;
	static boolean flag = true;
	static long[] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		len = s.length();
		dp = new long[len + 1];
		
		find(s, len);
		
		if(!flag) System.out.println(0);
		else System.out.println(dp[len]);
		
		sc.close();
	}
	private static long find(String s, int idx) {
		
		if( !flag ) return dp[idx] = 0;
		
		int target;
		if(idx == 1) {
			target = Integer.parseInt(s.substring(0, 1));
			if(target > 0 && target < 10) return dp[1] = 1;
			else return dp[1] = 0;
		}
		
		if(idx == 2) {
			target = Integer.parseInt(s.substring(0, 2));
			if(target > 9 && target < 27 && target != 10 && target != 20) return dp[2] = find(s, 1) + find(s, 1);
			else if((target > 26 && target % 10 != 0) || (target > 0 && target < 10) || target == 10 || target == 20) return dp[2] = find(s, 1);
			else {
				flag = false;
				return dp[idx] = 0;
			}
		}
		
		if(dp[idx] > 0) return dp[idx] % 1000000;
		
		target = Integer.parseInt(s.substring(idx - 2, idx));
		if(target > 9 && target < 27 && target != 10 && target != 20) return dp[idx] = (((find(s, idx - 2) % 1000000) + (find(s, idx - 1) % 1000000)) % 1000000);
		else if((target > 26 && target % 10 != 0) || (target > 0 && target < 10)) return dp[idx] = (find(s, idx - 1) % 1000000);
		else if(target == 10 || target == 20) return dp[idx] = (find(s, idx - 2) % 1000000);
		else {
			flag = false;
			return dp[idx] = 0;
		}
		
	}
}


/*
 * 문자열 2 -> 경우의 수 1개 (2) 
 * 문자열 25 -> 경우의 수 2개 (2,5) , (25)
 * 문자열 251 -> 경우의 수 2개 (2, 5, 1), (25, 1)
 * 문자열 2511 -> 경우의 수 4개 (2, 5, 1, 1), (2, 5, 11), (25, 1, 1), (25, 11)
 * 문자열 25112 -> 경우의 수 6개 (2, 5, 1, 1, 2), (2, 5, 11, 2), (25, 1, 1, 2), (25, 11, 2), (2, 5, 1, 12), (25, 1, 12)
 * 문자열 251121 -> 경우의 수 10개 (2, 5, 1, 1, 2, 1), (2, 5, 11, 2, 1), (25, 1, 1, 2, 1), (25, 11, 2, 1), (2, 5, 1, 12, 1), (25, 1, 12, 1), (2, 5, 1, 1, 21), (2, 5, 11, 21), (25, 1, 1, 21), (25, 11, 21)
 * 문자열 2511214 -> 경우의 수 16개 (2, 5, 1, 1, 2, 1, 4), (2, 5, 11, 2, 1, 4), (25, 1, 1, 2, 1, 4), (25, 11, 2, 1, 4), (2, 5, 1, 12, 1, 4), (25, 1, 12, 1, 4), (2, 5, 1, 1, 21, 4), (2, 5, 11, 21, 4), (25, 1, 1, 21, 4), (25, 11, 21, 4)
 *		   				      (2, 5, 1, 1, 2, 14), (2, 5, 11, 2, 14), (25, 1, 1, 2, 1, 14), (25, 11, 2, 14), (2, 5, 1, 12, 14), (25, 1, 21, 14)
 * 그 전 숫자와 결합시에 불가능하면 그 값 그대로 -> 
 * 
 * 2, 2, 2, 25, 25, 25 -- 2, 25
 * dp[1] => 5, 5, 5, 1,  1,  11 -- 5, 1, 11
 * dp[2] => 1, 1, 11, 1, 14, 4  -- 1, 11, 14, 4
 * dp[3] => 1, 14, 4, 4         -- 1, 14, 4
 * dp[4] => 4
 * 
 * dp[i - 2] + dp[i - 1] = dp[i] -> 1 ~ 26일 때
 * dp[i - 1] = dp[i] -> 거기서 벗어날 때
 * 
 * 하나가 추가될 때 그 전 숫자와 결합 시 26 이하면 두배
 * 아니면 그대로?
 * 
 * 
 * 20114
 * 20, 1, 1, 4
 * 20, 11, 4
 * 20, 1, 14
 * 
 * 1 -> (1)
 * 11 -> (1, 1), (11)
 * 111 -> (1,1,1), (11,1), (1,11)
 * 1110 -> (1,1,10), (11,10)
 * 11101 -> (1,1,10,1), (11,10,1)
 * 
 * 1, 1, 10, 12
 * 1, 1, 10, 1, 2
 * 11, 10, 1, 2
 * 11, 10, 12
 * 
 */