import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int start = sc.nextInt();
		int end = sc.nextInt();
		Map<String, Integer> map = new TreeMap<>();
		for(int i=start; i <= end; i++) map.put(changeText(i), i);
		
		int index = 0;
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			if(index == 10) {
				sb.append("\n");
				index = 0;
			}
			index++;
			sb.append(entry.getValue() + " ");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
	
	private static String changeText(int num) {
		StringBuilder sb = new StringBuilder();
		while(num > 0) {
			int target = num % 10;
			switch(target) {
				case 1 :
					sb.insert(0, "one ");
					break;
				case 2 :
					sb.insert(0, "two ");
					break;
				case 3 :
					sb.insert(0, "three ");
					break;
				case 4 :
					sb.insert(0, "four ");
					break;
				case 5 :
					sb.insert(0, "five ");
					break;
				case 6 :
					sb.insert(0, "six ");
					break;
				case 7 :
					sb.insert(0, "seven ");
					break;
				case 8 :
					sb.insert(0, "eight ");
					break;
				case 9 :
					sb.insert(0, "nine ");
					break;
				case 0 :
					sb.insert(0, "zero ");
					break;
			}
			
			num /= 10;
		}
		
		return sb.toString();
	}
}