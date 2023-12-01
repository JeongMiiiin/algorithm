import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int arr1Len = Integer.parseInt(st.nextToken());
			if(arr1Len == 0) break;
			int[] arr1 = new int[arr1Len];
			for(int i=0; i < arr1Len; i++) arr1[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			int arr2Len = Integer.parseInt(st.nextToken());
			int[] arr2 = new int[arr2Len];
			for(int i=0; i < arr2Len; i++) arr2[i] = Integer.parseInt(st.nextToken());
			
			int t1 = 0, t2 = 0, sum = 0, i = 0, j = 0;
			while(i < arr1Len && j < arr2Len) {
				if(arr1[i] == arr2[j]) {
					sum += Math.max(t1, t2);
					sum += arr1[i];
					t1=t2=0;
					i++;
					j++;
				}
				else if(arr1[i] < arr2[j] && i < arr1Len) t1 += arr1[i++];
				else if(arr1[i] > arr2[j] && j < arr2Len) t2 += arr2[j++];
			}
			while(i < arr1Len) t1 += arr1[i++];
			while(j < arr2Len) t2 += arr2[j++];
			sum += Math.max(t1, t2);
			sb.append(sum + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}