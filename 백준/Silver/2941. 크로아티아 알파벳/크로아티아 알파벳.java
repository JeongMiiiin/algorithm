import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 백준 2941 - 크로아티아 알파벳
 * 예전에는 운영체제에서 크로아티아 알파벳을 입력할 수 없었음.
 * 따라서 다음과 같이 크로아티아 알파벳을 변경해서 입력했다.
 * č -> c=
 * ć -> c-
 * dž -> dz=
 * đ -> d-
 * lj -> lj
 * nj -> nj
 * š -> s=
 * ž -> z=
 * 위 목록에 없는 알파벳은 한 글자씩 센다
 * 입력으로 주어진 단어가 몇 개의 크로아티아 알파벳으로 이루어져 있는지 출력
 * */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		List<String> changeAlphabetList = new ArrayList<String>(Arrays.asList(new String[]{"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="}));
		
		char[] inputs = br.readLine().toCharArray();
		int result = 0;
		for(int i=0; i < inputs.length; i++) {
			char cur = inputs[i];
			//조건에 걸릴 수 있는 경우
			if((cur == 'j' || cur == '-' || cur == '=') && i > 0) {
				String compare = Character.toString(inputs[i - 1]) + Character.toString(cur);
				int targetIdx = changeAlphabetList.indexOf(compare);
				if(targetIdx > -1) { //바꿔야하는 알파벳에 속할 경우
					result--;
					//dz=로 치환할 수 있는 경우
					if(targetIdx == 7 && i - 1 > 0 && inputs[i - 2] == 'd') result--; 
				}
			}
			result++; //알파벳 수 올리기
		}
		
		System.out.println(result);
		br.close();
	}
}