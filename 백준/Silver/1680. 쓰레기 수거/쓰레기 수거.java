import java.util.Scanner;

/*
 * 백준 1680 - 쓰레기 수거
 * 1. 쓰레기의 양이 용량에 도달했을 때
 * 2. 그 지점의 쓰레기를 실었을 때, 쓰레기차의 용량을 넘게될 때
 * 3. 더 이상 쓰레기를 실을 지점이 없을 때
 * */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for(int t=1; t <= T; t++) {
			int W = sc.nextInt(); //쓰레기 차의 용량
			int N = sc.nextInt(); //지점
			int curTrash = 0, curPos = 0; //현재 쓰레기 양과 위치
			int result = 0; //움직인 거리
			for(int i=0; i < N; i++) {
				int pos = sc.nextInt(); //지점 거리
				int trash = sc.nextInt(); //해당 쓰레기 양
				result += pos - curPos; 
				if(curTrash + trash < W) curTrash += trash; //쓰레기를 담아도 넘치지 않을 때
				else if(curTrash + trash == W) { //쓰레기의 용량에 도달했을 때
					result += pos; //쓰레기장에 두고 오기
					curTrash = 0; //현재 쓰레기 비움 처리
					if(i < N - 1) result += pos; //아직 가야할 곳이 남았을 때
				} else { //용량이 넘칠 때
					result += pos * 2;
					curTrash = trash;
				}
				curPos = pos;
			}
			if(curTrash > 0) result += curPos; //마지막 지점에서 쓰레기를 갖고 있을 때
			sb.append(result + "\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
}