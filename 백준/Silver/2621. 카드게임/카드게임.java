import java.util.Scanner;

public class Main {
	private static String cardColor[]=new String[5];
	private static int cardNumber[]=new int[5];
	private static int cntColor[]=new int[4];	// 0:R, 1:B, 2:Y, 3:G
	private static int cntNumber[]=new int[10];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<5;i++) {
			cardColor[i]=sc.next();
			cardNumber[i]=sc.nextInt();
			cntNumber[cardNumber[i]]++;
			if(cardColor[i].equals("R")) cntColor[0]++;
			else if(cardColor[i].equals("B")) cntColor[1]++;
			else if(cardColor[i].equals("Y")) cntColor[2]++;
			else if(cardColor[i].equals("G")) cntColor[3]++;
		}
		
		int start=0;
		int result=0;
		boolean check1=false;
		boolean check2=false;
		boolean check3=false;
		boolean check4=false;
		boolean check5=true;
		boolean check6=false;
		boolean check7=false;
		boolean check8=false;
		for(int i=9;i>0;i--) {	// 9번 규칙
			if(cntNumber[i]==1) {
				result=100+i;
				break;
			}
		}
		for(int i=1;i<=9;i++) {	// 1이 제일 처음 나오는 위치
			if(cntNumber[i]==1) {
				start=i;
				break;
			}
		}
		for(int i=0;i<4;i++) {
			if(cntColor[i]==5) {
				check1=true;
				check4=true;
			}
		}
		if(check1) {	
			for(int i=start;i<start+5;i++) {
				if(cntNumber[i]!=1) {
					check1=false;
					break;
				}
			}
			if(check1) {	// 1번 규칙
				result=900+start+4;
			}
			else {	// 4번 규칙
				for(int j=9;j>0;j--) {
					if(cntNumber[j]>0) {
						result=600+j;
						break;
					}
				}
			}
		}
		for(int i=1;i<=9;i++) {
			if(cntNumber[i]==4) {	// 2번 규칙
				result=800+i;
				break;
			}
			if(cntNumber[i]==3) {
				for(int j=1;j<=9;j++) {	// 3번 규칙
					if(cntNumber[j]==2) {
						check3=true;
						result=700+i*10+j;
						break;
					}
				}
				if(!check3) {	// 6번 규칙
					result=400+i;
					break;
				}
			}
			if(cntNumber[i]==2) {
				if(i<9) {
					for(int j=i+1;j<=9;j++) {
						if(cntNumber[j]==2) {	// 7번 규칙
							check7=true;
							result=300+j*10+i;
							break;
						}
					}
				}
				if(!check7 && !check3) {	// 8번 규칙
					result=200+i;
				}
			}
		}
		if(start>0 && start+5<9 && !check1) {
			for(int i=start;i<start+5;i++) {
				if(cntNumber[i]!=1) check5=false;
			}
			if(check5) result=500+start+4;	// 5번 규칙
		}
		System.out.println(result);
		sc.close();
	}
}