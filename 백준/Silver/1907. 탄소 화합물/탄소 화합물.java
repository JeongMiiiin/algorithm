import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String[] n3 = s.split("=");
		String[] n2 = n3[0].split("\\+");
		int[] x1 = new int[3], x2 = new int[3], x3 = new int[3];
		for(int i=0; i < n2[0].length(); i++) {
			char c = n2[0].charAt(i);
			if(c - '0' < 9) continue;
			switch(c) {
			case 'C' :
				if(i + 1 < n2[0].length() && n2[0].charAt(i + 1) - '0' <= 9) x1[0] += n2[0].charAt(i + 1) - '0';
				else x1[0]++;
				break;
			case 'O' :
				if(i + 1 < n2[0].length() && n2[0].charAt(i + 1) - '0' <= 9) x1[1] += n2[0].charAt(i + 1) - '0';
				else x1[1]++;
				break;
			case 'H' :
				if(i + 1 < n2[0].length() && n2[0].charAt(i + 1) - '0' <= 9) x1[2] += n2[0].charAt(i + 1) - '0';
				else x1[2]++;
				break;
			}
		}
		
		for(int i=0; i < n2[1].length(); i++) {
			char c = n2[1].charAt(i);
			if(c - '0' < 9) continue;
			switch(c) {
			case 'C' :
				if(i + 1 < n2[1].length() && n2[1].charAt(i + 1) - '0' <= 9) x2[0] += n2[1].charAt(i + 1) - '0';
				else x2[0]++;
				break;
			case 'O' :
				if(i + 1 < n2[1].length() && n2[1].charAt(i + 1) - '0' <= 9) x2[1] += n2[1].charAt(i + 1) - '0';
				else x2[1]++;
				break;
			case 'H' :
				if(i + 1 < n2[1].length() && n2[1].charAt(i + 1) - '0' <= 9) x2[2] += n2[1].charAt(i + 1) - '0';
				else x2[2]++;
				break;
			}
		}
		
		for(int i=0; i < n3[1].length(); i++) {
			char c = n3[1].charAt(i);
			if(c - '0' < 9) continue;
			switch(c) {
			case 'C' :
				if(i + 1 < n3[1].length() && n3[1].charAt(i + 1) - '0' <= 9) x3[0] += n3[1].charAt(i + 1) - '0';
				else x3[0]++;
				break;
			case 'O' :
				if(i + 1 < n3[1].length() && n3[1].charAt(i + 1) - '0' <= 9) x3[1] += n3[1].charAt(i + 1) - '0';
				else x3[1]++;
				break;
			case 'H' :
				if(i + 1 < n3[1].length() && n3[1].charAt(i + 1) - '0' <= 9) x3[2] += n3[1].charAt(i + 1) - '0';
				else x3[2]++;
				break;
			}
		}
		
		int firstCnt = 1, secondCnt = 1, thirdCnt = 1;
		outer : for(firstCnt = 1; firstCnt <= 10; firstCnt++) {
			for(secondCnt = 1; secondCnt <= 10; secondCnt++) {
				for(thirdCnt = 1; thirdCnt <= 10; thirdCnt++) {
					//조건이 맞는지 확인
					boolean flag = true;
					for(int idx = 0; idx < 3; idx++) {
						if(firstCnt * x1[idx] + secondCnt * x2[idx] != thirdCnt * x3[idx]) { //조건이 맞지 않으면 종료
							flag = false;
							break;
						}
					}
					
					//조건이 맞으면 종료
					if(flag) break outer;
				}
			}
		}
		
		System.out.println(firstCnt + " " + secondCnt + " " + thirdCnt);
		sc.close();
	}
}