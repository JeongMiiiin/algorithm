import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * SWEA 6808 - 규영이와 인영이의 카드게임
 * 1 - 18까지의 수가 적힌 18장의 카드로 게임
 * 9장씩 갖고, 9라운드로 진행
 * 한 라운드당 하나의 카드를 내고, 이긴쪽이 두 카드의 합만큼의 점수를 가짐. 진 쪽은 점수 없음.
 * 9라운드 끝나고 총 점수가 높은 쪽이 승자
 * 규영이의 라운드 카드 순서가 정해지고, 이긴것과 진 것의 경우의 수를 계산하라
*/
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int win, lose;
	static int R = 9;
	static int[] roundHome, roundOpposite, numbers;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			win = 0;
			lose = 0;
			
			st = new StringTokenizer(br.readLine(), " ");
			
			int[] checkNum = new int[R * 2];
			
			//규영이의 라운드 카드 순서 저장
			roundHome = new int[R];
			for(int i=0; i < R; i++) {
				int n = Integer.parseInt(st.nextToken());
				roundHome[i] = n;
				checkNum[n - 1] = 1;
			}
			
			//라운드에서 규영이가 뭘 낼지 저장하는 배열
			numbers = new int[R];
			
			//인영이의 카드들 저장
			roundOpposite = new int[R];
			for(int i=0, idx=0; i < checkNum.length; i++)
				if(checkNum[i] == 0) roundOpposite[idx++] = i + 1;
			
			perm(0,0);
			
			
			//출력
			System.out.println("#" + t + " " + win + " " + lose);
		}
	}

	private static void perm(int cnt, int flag) {
		if(cnt == R) {
			int homeScore = 0;
			int oppositeScore = 0;
			for(int i=0; i < R; i++) {
				if(roundHome[i] > numbers[i]) homeScore += roundHome[i] + numbers[i];
				else oppositeScore += roundHome[i] + numbers[i];
			}
			
			if(homeScore > oppositeScore) win++;
			else if(homeScore < oppositeScore) lose++;
			
			return;
		}
		
		for(int i=0; i < R; i++) {
			if((flag & (1 << i)) != 0) continue;
			numbers[cnt] = roundOpposite[i];
			perm(cnt + 1, (flag | 1 << i));
		}
	}
}
