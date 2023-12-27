import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] arr = sc.next().toCharArray();
		boolean flag = true;
		for(int i=0; i < arr.length / 2; i++) {
			if(arr[i] != arr[arr.length - 1 - i]) {
				flag = false;
				break;
			}
		} 
		
		System.out.println(flag ? 1 : 0);
		sc.close();
	}
}