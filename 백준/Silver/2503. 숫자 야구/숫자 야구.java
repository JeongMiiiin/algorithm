import java.util.Scanner;

public class Main {
	static class Candidate {
		int first, second, third, strike, ball;
		public Candidate(int first, int second, int third, int strike, int ball) {
			this.first = first;
			this.second = second;
			this.third = third;
			this.strike = strike;
			this.ball = ball;
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[504];
		int idx = 0;
		for(int i=1; i <= 9; i++) {
			for(int j=1; j <= 9; j++) {
				if(i == j) continue;
				for(int z=1; z <= 9; z++) {
					if(i == z || j == z) continue;
					arr[idx++] = (int) (i * Math.pow(10, 2) + j * 10 + z);
				}
			}
		}
		boolean[] check = new boolean[504];
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Candidate[] candidates = new Candidate[N];
		for(int i=0; i < N; i++) {
			int target = sc.nextInt();
			int strike = sc.nextInt();
			int ball = sc.nextInt();
			int[] temp = changeNum(target);
			candidates[i] = new Candidate(temp[0], temp[1], temp[2], strike, ball);
		}
		
		int result = 0;
		Candidate cur;
		for(int i=0; i < check.length; i++) {
			boolean flag = true;
			int[] temp = changeNum(arr[i]);
			for(int j=0; j < N; j++) {
				cur = candidates[j];
				int strikeCnt = 0, ballCnt = 0;
				if(cur.first == temp[0]) strikeCnt++;
				else if(cur.first == temp[1] || cur.first == temp[2]) ballCnt++;
				
				if(cur.second == temp[1]) strikeCnt++;
				else if(cur.second == temp[0] || cur.second == temp[2]) ballCnt++;
				
				if(cur.third == temp[2]) strikeCnt++;
				else if(cur.third == temp[0] || cur.third == temp[1]) ballCnt++;
				
				if(cur.strike != strikeCnt || cur.ball != ballCnt) {
					flag = false;
					break;
				}
			}
			
			if(flag) result++; //체크했을 때 통과하면 result 증가
		}
		
		System.out.println(result);
		sc.close();
	}
	
	private static int[] changeNum(int target) {
		int[] result = new int[3];
		
		result[2] = target % 10;
		target /= 10;
		result[1] = target % 10;
		target /= 10;
		result[0] = target;
		return result;
	}
}