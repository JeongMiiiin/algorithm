import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 백준 21921 - 블로그
 * 
 * 주어지는 값
 * N : 지난 일 수
 * X : 확인하고 싶은 기간 일 수
 * 둘째 줄 : 1 ~ N일차까지의 하루 방문자 수
 * 
 * 블로그를 시작한 지 N일이 지남
 * 찬솔이는 X일 동안 가장 많이 들어온 방문자 수와 그 기간들을 알고 싶음
 * 
 * 
 * 
*/
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int X = sc.nextInt();
		int[] total = new int[N];
		Queue<Integer> slide = new LinkedList<>();
		
		
		int cur = 0;
		for(int i=0; i < N; i++) total[i] = sc.nextInt();
		for(int i=0; i < X; i++) {
			cur += total[i];
			slide.offer(total[i]);
		}
		
		int max = cur;
		int maxCnt = 1;
		for(int i=X; i < N; i++) {
			cur -= slide.poll();
			cur += total[i];
			slide.offer(total[i]);
			if(cur > max) {
				maxCnt = 1;
				max = cur;
			} else if(cur == max) maxCnt++;
		}
		
		if(max == 0) System.out.println("SAD");
		else {
			System.out.println(max);
			System.out.println(maxCnt);
		}
		
		sc.close();
	}
}