import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * 백준 1759번 - 암호 만들기
 * 주어지는 값
 * L : 암호길이
 * C : 암호를 조합할 수 있는 단어 개수
 * 출력값
 * 사전식으로 가능성 있는 암호 모두 출력
 * 사전식 : 알파벳이 증가하는 방식
*/
public class Main {
	static List<String> gatherList = new ArrayList<>(Arrays.asList(new String[]{"a","e","i","o","u"}));
	static int L, C;
	static String[] inputs, selected;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		C = sc.nextInt();
		sc.nextLine();
		inputs = sc.nextLine().split(" ");
		Arrays.sort(inputs);
		selected = new String[L];
		
		comb(0,0,0,0);
		
		sc.close();
	}
	private static void comb(int cnt, int start, int gather, int consonant) {
		if(cnt == L) {
			if(gather < 1 || consonant < 2) return; //최소조건에 충족하지 않는 경우
			StringBuilder sb = new StringBuilder();
			for(int i=0; i < L; i++) sb.append(selected[i]);
			System.out.println(sb.toString());
			return;
		}
		
		for(int i=start; i < C; i++) {
			selected[cnt] = inputs[i];
			//추가하는 값이 모음인 경우
			if(gatherList.contains(selected[cnt])) comb(cnt + 1, i + 1, gather + 1, consonant);
			else comb(cnt + 1, i + 1, gather, consonant + 1);
		}
	}
}
