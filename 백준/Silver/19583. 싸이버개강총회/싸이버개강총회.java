import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int startTime = changeTime(st.nextToken());
		int endTime = changeTime(st.nextToken());
		int strimingEndTime = changeTime(st.nextToken());
		Set<String> attend = new HashSet<>();
		int result = 0;
		while(true) {
			String s = br.readLine();
			if(s == null || s.equals("")) break;
			st = new StringTokenizer(s, " ");
			int targetTime = changeTime(st.nextToken());
			if(targetTime <= startTime) attend.add(st.nextToken());
			else if(targetTime >= endTime && targetTime <= strimingEndTime) {
				String target = st.nextToken();
				if(attend.contains(target)) {
					result++;
					attend.remove(target);
				}
			}
		}
		
		System.out.println(result);
		br.close();
	}
	
	private static int changeTime(String time) {
		int result = 0;
		String[] info = time.split(":");
		result += Integer.parseInt(info[0]) * 60;
		result += Integer.parseInt(info[1]);
		return result;
	}
}