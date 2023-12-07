import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Double total = 0.0, score = 0.0;
		for(int i=0; i < 20; i++) {
			String subject = sc.next(); //과목명
			Double cur = Double.parseDouble(sc.next()); //학점
			char[] curScoreInfo = sc.next().toCharArray();
			if(curScoreInfo[0] != 'P') { //패스가 아니면
				Double curScore = 0.0;
				switch(curScoreInfo[0]) {
					case 'A' : curScore += 4; break;
					case 'B' : curScore += 3; break;
					case 'C' : curScore += 2; break;
					case 'D' : curScore += 1; break;
					default : break;
				}
				if(curScoreInfo.length > 1 && curScoreInfo[1] == '+') curScore += 0.5;
				
				score += cur * curScore;
				total += cur;
			}
		}
		System.out.println(score / total);
		
		sc.close();
	}
}