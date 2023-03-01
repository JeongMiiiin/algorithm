import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] TList = new int[T];
		
		for(int i=0; i < T; i++)
			TList[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(TList);
		
		int avg = getAvg(TList);
		int mid = getMid(TList);
		int dis = getDis(TList);
		int fre = getFre(TList, dis);
		
		System.out.println(avg);
		System.out.println(mid);
		System.out.println(fre);
		System.out.println(dis);
		
	}
	
	private static int getAvg(int[] list) {
		double sum = 0;
		for(int n : list)
			sum += n;
		return (int) Math.round(sum / list.length);
	}
	
	private static int getMid(int[] list) {
		return list[list.length / 2];
	}
	
	private static int getFre(int[] list, int distance) {
		int fre = 0;
		int[] freList = new int[distance + 1];
		for(int i=0; i < list.length; i++)
			freList[list[i] - list[0]]++;
		
		int max = 0;
		int num = 1;
		int idx = 0;
		for(int i=0; i < freList.length; i++) {
			if(max == freList[i] && num == 1) {
				idx = i;
				num++;
			}
			if(max < freList[i] && max != freList[i]) {
				max = freList[i];
				idx = i;
				num = 1;
			}
		};
		
		return idx + list[0];
	}
	
	private static int getDis(int[] list) {
		return Math.abs(list[list.length - 1] - list[0]);
	}
}
