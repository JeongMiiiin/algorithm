import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 찾기 기능을 구현
 * 
 * 주어지는 값
 * T : 문자열
 * P : 패턴
 * 
 * 두 개의 문자열 P와 T에 대해
 * 문자열 P가 문자열 T 중간에 몇 번
 * 어느 위치에서 나타나는지 알아내는 문제를 '문자열 매칭'
 * 이때 P는 패턴, T는 텍스트
 * 
 * KMP를 적용하여
 * P가 몇번 나타나는지와
 * P가 나타나는 위치를 공백으로 구분하여 출력하라
 * 
*/
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] T = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();
		
		int Tlen = T.length;
		int Plen = P.length;
		
		//부분일치 테이블 만들기
		int[] pi = new int[Plen];
		for(int i=1, j=0; i < Plen; i++) {
			while(j > 0 && P[i] != P[j]) j = pi[j - 1];
			
			if(P[i] == P[j]) pi[i] = ++j;
			else pi[i] = 0;
		}
		
		int cnt = 0;
		List<Integer> list = new ArrayList<>();
		// i : 텍스트 포인터, j : 패턴 포인터
		for(int i=0, j=0; i < Tlen; i++) {
			while(j > 0 && T[i] != P[j]) j = pi[j - 1];
			
			if(T[i] == P[j]) {
				if(j == Plen - 1) {
					cnt++;
					list.add(i - j);
					j = pi[j];
				} else j++;
			}
		}
		
		System.out.println(cnt);
		if(cnt>0) {
			StringBuilder sb = new StringBuilder();
			for(int n : list) sb.append(n + 1 + " ");
			System.out.println(sb.toString());
		}
	}
}