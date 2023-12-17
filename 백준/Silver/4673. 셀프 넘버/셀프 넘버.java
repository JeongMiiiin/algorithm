public class Main {
	static int max = 10001;
	public static void main(String[] args) {
		int[] arr = new int[max];
		for(int i=1; i < max; i++) {
			int result = calcNum(i);
			if(result < max) arr[result]++;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i < max; i++) if(arr[i] == 0) sb.append(i + "\n");
		System.out.println(sb.toString());
	}
	
	private static int calcNum(int target) {
		int result = target;
		while(target > 0) {
			result += target % 10;
			target /= 10;
		}
		
		return result;
	}
}