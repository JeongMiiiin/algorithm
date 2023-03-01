import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	/*
	 * N개의 단어 정렬
	 * 길이가 짧은것부터
	 * 길이가 같으면 사전순으로
	*/
	public static void main(String[] args) throws Exception{
		
		int T = Integer.parseInt(br.readLine());
		
		String[] strList = new String[T];
 		
		Set<String> strSet = new HashSet<String>();
		
		int min = Integer.MAX_VALUE;
		int max = 0;
		//입력
		for(int i = 0; i < T; i++) {
			StringBuilder sb = new StringBuilder(br.readLine());
			if(sb.length() < min) min = sb.length();
			if(sb.length() > max) max = sb.length();
			strList[i] = sb.toString();
		}
	
		//중복제거
		strList = Arrays.stream(strList).distinct().toArray(String[]::new);
		
		//정렬
		Arrays.sort(strList);
	
		
		StringBuilder sb = new StringBuilder();
		
		while(min <= max) {
			for(String s : strList)
				if(min == s.length()) bw.write(s + "\n");
			
			min++;
		}
		
		bw.close();
	}
}