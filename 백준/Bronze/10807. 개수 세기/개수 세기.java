import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] nList = new int[N];
		for(int i=0; i < N; i++) nList[i] = Integer.parseInt(st.nextToken());
		int findNum = Integer.parseInt(br.readLine());
		int result = 0;
		for(int i=0; i < N; i++) if(nList[i] == findNum) result++;
		System.out.println(result);
		br.close();
	}
}