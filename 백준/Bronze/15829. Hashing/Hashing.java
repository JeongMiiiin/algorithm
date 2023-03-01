import java.util.Scanner;

//백준 15829번 해싱
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int sum = 0, idx = 0;
		for(char c : sc.next().toCharArray())
			sum += ((c - '0') - 48) * (Math.pow(31, idx++));
		
		System.out.println(sum);
	}
}