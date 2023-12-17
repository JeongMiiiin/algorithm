import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] cntList = new int[10];
		while(N > 0) {
			cntList[N % 10]++;
			N /= 10;
		}
		//6과 9번 통일
		int sum = (cntList[6] + cntList[9]) % 2 == 0 ? (cntList[6] + cntList[9]) / 2 : (cntList[6] + cntList[9]) / 2 + 1;
		cntList[6] = sum;
		cntList[9] = sum;
		//필요한 세트 구하기
		int result = 0;
		for(int i=0; i < 10; i++) result = Math.max(result, cntList[i]);
		
		System.out.println(result);
		sc.close();
	}
}