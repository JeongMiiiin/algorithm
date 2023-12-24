import java.util.Scanner;

/*
 * 백준 8979 - 올림픽
 * 잘하는 국가의 기준
 * 1. 금메달 수가 더 많은 나라
 * 2. 금메달 수가 같다면, 은메달 수가 더 많은 나라
 * 3. 금, 은메달 수가 모두 같다면, 동메달 수가 더 많은 나라
 * 각 국가는 1 ~ N 사이의 정수로 표현 됨.
 * 한 국가의 등수는 (자신보다 더 잘한 나라 수) + 1로 정의됨.
 * 금, 은, 동이 모두 같다면 두 나라의 등수는 같음. -> 공동
 * 입력
 * 첫 줄 -> 국가의 수 N, 등수를 알고 싶은 국가 K
 * N개의 줄 -> 각 국가의 메달 정보
 * */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //국가 수
		int K = sc.nextInt(); //알고싶은 국가 번호
		//스코어 세팅
		int[][] score = new int[N + 1][3];
		for(int i=1; i <= N; i++) {
			int target = sc.nextInt(); //처음 국가 번호 처리
			score[target][0] = sc.nextInt(); //금
			score[target][1] = sc.nextInt(); //은
			score[target][2] = sc.nextInt(); //동
		}
		//구해야하는 랭킹 값 세팅
		int rank = 1;
		int gold = score[K][0], silver = score[K][1], bronze = score[K][2];
		for(int i=1; i <= N; i++) {
			if(i == K) continue; //자기 자신이면 통과
			
			if(score[i][0] > gold || //금메달로 앞서면
					(score[i][0] == gold && score[i][1] > silver) || //금메달은 같으나 은메달에서 앞서면
					(score[i][0] == gold && score[i][1] == silver && score[i][2] > bronze)) rank++; //금메달 은메달이 같으나 동메달에서 앞서면
		}
		System.out.println(rank);
		sc.close();
	}
}