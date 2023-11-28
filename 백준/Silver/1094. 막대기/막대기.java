import java.util.Scanner;

/*
 * 백준 1094 - 막대기
 * 길이가 64cm인 막대
 * 길이가 Xcm인 막대가 가지고 싶어졌음.
 * 원래 가지고 있던 막대를 더 작은 막대로 자른 다음에, 풀로 붙여서 길이가 Xcm인 막대로 만들려고 함.
 * 막대를 자르는 가장 쉬운 방법은 절반으로 자르기.
 * 처음에는 64cm인 막대 하나만 가지고 있음.
 * 1. 가지고 있는 막대의 길이를 모두 더한다. 이때 합이 X보다 크다면, 아래와 같은 과정을 반복.
 * 2. 가지고 있는 막대 중 길이가 가장 짧은 것을 절반으로 자름.
 * 3. 만약, 위에서 자른 막대의 절반 중 하나를 버리고 남아있는 막대의 길이의 합이 X보다 크거나 같다면, 위에서 자른 막대의 절반 중 하나를 버린다.
 * 4. 이제, 남아있는 모든 막대를 풀로 붙여서 Xcm를 만든다.
 * 
 * X가 주어졌을 때, 위의 과정을 거친다면, 몇 개의 막대를 풀로 붙여서 Xcm를 만들 수 있는지 구하라.
 * 64
 * 32 1개
 * 16 16
 * 8 16
 * 4 4 16
 * 2 2 4 16
 * 1 2 4 16
 * 
 * 8 16 2개
 * 4 16 3개
 * 2 2 4 16
 * */

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		int result = 1, cur = 64, total = 64;
		while(total != X) {
			if(cur == 1) {
				result = -1;
				break;
			}
			cur /= 2;
			if(cur == X) { //자른게 바로 일치하면 1로 끝
				result = 1;
				break;
			} else if(total - cur >= X){ //자른 막대의 절반 중 하나를 버리고도 막대의 길이의 합이 X보다 크거나 같으면 막대 하나 버리기.
					total -= cur;
			} else result++; //막대가 추가됨.
		}
		
		System.out.println(result);
		sc.close();
	}
}