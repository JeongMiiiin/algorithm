import java.util.Scanner;

public class Main {
	static int recurCnt;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for(int t=0; t < T; t++) {
        	recurCnt = 0;
        	String s = sc.nextLine();
        	System.out.println(isPalindrome(s) + " " + recurCnt);
        }
        sc.close();
    }
	
	public static int recursion(String s, int l, int r){
		recurCnt++;
        if(l >= r) return 1;
        else if(s.charAt(l) != s.charAt(r)) return 0;
        else return recursion(s, l+1, r-1);
    }
    public static int isPalindrome(String s){
        return recursion(s, 0, s.length()-1);
    }
}
