import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] numArr = new int[10];
		long total = 0;
		for(char c : sc.nextLine().toCharArray()) {
			numArr[c - '0']++;
			total += c - '0';
		} 
		StringBuilder sb = new StringBuilder();
		if(numArr[0] > 0 && total % 3 == 0) for(int i=9; i >= 0; i--) for(int j=0; j < numArr[i]; j++) sb.append(i);
		else sb.append(-1);
		
		System.out.println(sb.toString());
		sc.close();
	}
}