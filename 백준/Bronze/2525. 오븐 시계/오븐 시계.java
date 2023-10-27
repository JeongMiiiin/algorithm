import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int hour = sc.nextInt();
		int minute = sc.nextInt();
		int plusMinute = sc.nextInt();
		minute += plusMinute;
		int nextHour = minute / 60;
		minute -= nextHour * 60;
		hour += nextHour;
		if(hour >= 24) hour -= 24;
		System.out.println(hour + " " + minute);
		sc.close();
	}
}