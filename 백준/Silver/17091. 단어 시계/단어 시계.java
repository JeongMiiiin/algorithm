import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String[] hours = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve"};
		String[] mins = {"o' clock", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "quarter", "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four", "twenty five", "twenty six", "twenty seven", "twenty eight", "twenty nine", "half"};
		Scanner sc = new Scanner(System.in);
		int h = Integer.parseInt(sc.nextLine());
		int m = Integer.parseInt(sc.nextLine());
		boolean flag = false;
		if(m > 30) {
			if(++h > 12) h = 1;
			m = 60 - m;
			flag = true;
		}
		if(m != 0) System.out.println(mins[m] + (m % 15 != 0 ? m != 1 ? " minutes " : " minute " : " ") + (flag ? "to " : "past ") + hours[h]);
		else System.out.println(hours[h] + " " + mins[m]);
		
		sc.close();
	}
}