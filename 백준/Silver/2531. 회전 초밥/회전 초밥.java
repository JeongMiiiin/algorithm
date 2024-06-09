import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for(int i=0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		int temp = 1;
		int[] check = new int[d + 1];
		check[c] = N;
		for(int i=0; i < k; i++) 
			if(check[arr[i]]++ == 0) temp++;
		
		int result = temp, prev = 0, next = k;
		
		while(prev < N) {
			if(check[arr[next++]]++ == 0) temp++;
			if(check[arr[prev++]]-- == 1) temp--;
			if(next >= N) next = 0;
			result = Math.max(result, temp);
		}
		
		System.out.println(result);
		br.close();
	}
}