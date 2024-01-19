import java.util.Arrays;
import java.util.Scanner;

/*
 * 백준 1900 - 레슬러
 * 레슬러는 모두 마술 링을 착용.
 * A와 B가 대결한다면 A의 경기력은 A의 힘 + B의 힘 * A 마술링 힘
 * 한 선수가 받는 금화의 수는 그가 이긴 경기 수 + 선수들의 줄에서 자기보다 앞에 있는데 자기가 이긴 선수의 수
 *  
 * */
public class Main {
	static class Wrestler implements Comparable<Wrestler>{
		int idx, power, ringPower, winCnt = 0;
		
		public Wrestler(int idx, int power, int ringPower) {
			this.idx = idx;
			this.power = power;
			this.ringPower = ringPower;
		}
		
		@Override
		public int compareTo(Wrestler o) {
			// TODO Auto-generated method stub
			return o.winCnt - this.winCnt;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		//세팅
		Wrestler[] arr = new Wrestler[N];
		for(int i=0; i < N; i++) arr[i] = new Wrestler(i + 1, sc.nextInt(), sc.nextInt());
		
		//대결 시작
		for(int i=0; i < N; i++) {
			for(int j=i + 1; j < N; j++) {
				int APower = arr[i].power + arr[j].power * arr[i].ringPower;
				int BPower = arr[j].power + arr[i].power * arr[j].ringPower;
				if(APower > BPower) arr[i].winCnt++; //a가 이기는 경우
				else arr[j].winCnt++; //b가 이기는 경우
			}
		}
		
		//정렬
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N; i++) sb.append(arr[i].idx + "\n");
		
		System.out.println(sb.toString());
		sc.close();
	}
}