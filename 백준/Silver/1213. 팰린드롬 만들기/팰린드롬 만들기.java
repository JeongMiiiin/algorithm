/*
 * 백준 1213 - 팰린드롬 만들기
 * 
 * 임한수의 영어 이름의 알파벳 순서를 적절히 바꿔서 팰린드롬을 만든다.
 * 영어 이름을 팰린드롬으로 바꾸는 프로그램 작성
 * 
 * 각각의 값을 담고, 차례대로 뿌려주기?
 * 길이가 홀수면 값들이 다 짝수이고, 하나만 홀수여야함
 * 길이가 짝수면 값들이 모두 짝수여야 함
 * A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
*/

import java.util.Scanner;

public class Main {
	
	static int[] alphabet = new int[26];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char[] c = sc.nextLine().toCharArray();
		
		int len = c.length;
		
		for(int i=0; i < len; i++) alphabet[c[i] - 'A']++;
		
		boolean oddStatus = len % 2 == 1 ? true : false;
		
		
		boolean flag = true;
		if(oddStatus) { //홀수인 경우 (하나만 홀수여야 함)
			int odd = 0;
			for(int i=0; i < 26; i++) if(alphabet[i] % 2 == 1) odd++;
			if(odd > 1) flag = false;
		} else { //짝수인 경우 (모두 다 짝수여야 함)
			for(int i=0; i < 26; i++) {
				if(alphabet[i] % 2 == 1) {
					flag = false;
					break;
				}
			}
		}
		
		if(flag) { //만들 수 있을 때
			StringBuilder start = new StringBuilder();
			StringBuilder end = new StringBuilder();
			int odd = -1;
			for(int i=0; i < 26; i++) {
				if(alphabet[i] > 0) {
					int cnt = alphabet[i] / 2;
					for(int j=0; j < cnt; j++) {
						start.append((char) (i + 65));
						end.insert(0, (char) (i + 65));
					}
					alphabet[i] -= (alphabet[i] / 2) * 2;
					if(alphabet[i] == 1) odd = i;
				}
			}
			
			if(odd > -1) start.append((char) (odd + 65));
			
			System.out.println(start.toString() + end.toString());
			
		} else System.out.println("I'm Sorry Hansoo");
		
		sc.close();
	}
}