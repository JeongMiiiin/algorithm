import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 백준 14501번 - 퇴사
 * 주어지는 값
 * N : 남은 일수
 * 두번째 줄부터 N번째 줄 : 그날 시작할 수 있는 상담의 소요 일수 및 이익
*/
public class Main {
	static int N, ans = -1;
	static Consult[] cList;
	static boolean[] selected;
	static class Consult { //상담에 대한 내용을 담는 변수
		int days;
		int profit;
		public Consult(int days, int profit) {
			this.days = days;
			this.profit = profit;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		cList = new Consult[N];
		selected = new boolean[N];
		for(int i=0; i < N; i++)
			cList[i] = new Consult(sc.nextInt(), sc.nextInt());
		
		subset(0);
		
		System.out.println(ans);
		sc.close();
		
	}
	
	private static void subset(int cnt) {
		if(cnt == N) {
			int sum = 0, days = 1;
			List<Consult> newCList = new ArrayList<>();
			
			for(int i=0; i < N; i++) {
				if(selected[i] && cList[i].days <= N - i) {
					if(newCList.size() == 0) {
						newCList.add(cList[i]);
						sum += cList[i].profit;
					} else if(newCList.get(newCList.size() - 1).days <= days){
						newCList.add(cList[i]);
						sum += cList[i].profit;
					}
					days = 1;
				} else days++;
				
			}
			ans = Math.max(ans, sum);
			return;
		}
		
		selected[cnt] = true;
		subset(cnt + 1);
		selected[cnt] = false;
		subset(cnt + 1);
	}
}
