import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String[] table = {"aespa","baekjoon","cau","debug","edge","firefox","golang","haegang","iu","java","kotlin","lol","mips","null","os","python","query","roka","solvedac","tod","unix","virus","whale","xcode","yahoo","zebra"};
		
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		StringBuilder sb = new StringBuilder();
		StringBuilder result = new StringBuilder();
		result.append("It's HG!\n");
		boolean flag = true;
		for(char c : s.toCharArray()) {
			sb.append(c);
			String target = sb.toString();
			int targetIdx = (int) target.charAt(0) - 97;
			if(sb.toString().length() >= table[targetIdx].length()) { //비교해야하는 문자와 길이가 같을 때
				if(!sb.toString().equals(table[targetIdx])) { //비교했는데 틀릴 때 break;
					flag = false;
					break;
				} else {
					result.append(target.charAt(0));
					sb.setLength(0);
				}
			}
		}
		if(sb.length() > 0) flag = false;
		System.out.println(flag ? result.toString() : "ERROR!");
		sc.close();
	}
}