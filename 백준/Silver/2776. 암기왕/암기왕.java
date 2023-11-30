import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 2776 - 암기왕
 * 
 * */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] nList = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i < N; i++) nList[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(nList);
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i < M; i++) {
				int target = Integer.parseInt(st.nextToken());
				int left = 0, right = N - 1;
				if(target == nList[left] || target == nList[right]) {
					sb.append("1\n");
					continue;
				}
				if(target < nList[left] || target > nList[right]) {
					sb.append("0\n");
					continue;
				}
				while(left < right) {
					int mid = (left + right) / 2;
					if(nList[mid] > target) right = mid - 1;
					else if(nList[mid] < target) left = mid + 1;
					else {
						left = mid;
						break;
					}
				}
				
				if(target == nList[left]) sb.append("1\n");
				else sb.append("0\n");
			}
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}