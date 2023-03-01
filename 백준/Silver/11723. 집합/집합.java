import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		List<Integer> NList = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for(int t=0; t < T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			String cmd = st.nextToken();
			int n = -1;
			switch(cmd) {
				case "add" :
					n = Integer.parseInt(st.nextToken());
					if( !NList.contains(n) ) NList.add(n);
					break;
				case "remove" :
					n = Integer.parseInt(st.nextToken());
					if( NList.contains(n) ) NList.remove(NList.indexOf(n));
					break;
				case "check" :
					n = Integer.parseInt(st.nextToken());
					if( NList.contains(n) ) sb.append(1 + "\n");
					else sb.append(0 + "\n");
					break;
				case "toggle" :
					n = Integer.parseInt(st.nextToken());
					if( NList.contains(n) ) NList.remove(NList.indexOf(n));
					else NList.add(n);
					break;
				case "all" :
					NList.removeAll(NList);
					for(int i=1; i <= 20; i++) NList.add(i);
					break;
				case "empty" :
					NList.removeAll(NList);
					break;
			}
			
		}
		System.out.println(sb.toString());
	}
}
