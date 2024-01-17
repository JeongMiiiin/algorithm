import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static class Serial implements Comparable<Serial>{
		String s;
		
		public Serial(String s) {
			this.s = s;
		}
		@Override
		public int compareTo(Serial o) {
			if(this.s.length() != o.s.length()) return this.s.length() - o.s.length(); //길이가 짧은 것
			else { //길이가 같다면
				int thisTotal = 0, oppositeTotal = 0;
				for(int i=0; i < this.s.length(); i++) {
					if(this.s.charAt(i) - '0' <= 9) thisTotal += this.s.charAt(i) - '0';
					if(o.s.charAt(i) - '0' <= 9) oppositeTotal += o.s.charAt(i) - '0';
				}
				if(thisTotal != oppositeTotal) return thisTotal - oppositeTotal; //자리수의 합이 다른 경우
				else { //자리수의 합도 같은 경우
					for(int i=0; i < this.s.length(); i++) if(this.s.charAt(i) != o.s.charAt(i)) return this.s.charAt(i) - o.s.charAt(i);
				}
			}
			
			return 0;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Serial[] arr = new Serial[N];
		for(int i=0; i < N; i++) arr[i] = new Serial(sc.nextLine());
		Arrays.sort(arr);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N; i++) sb.append(arr[i].s + "\n");
		System.out.println(sb.toString());
		sc.close();
	}
}