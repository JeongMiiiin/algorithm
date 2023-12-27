import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main {
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int N = Integer.parseInt(br.readLine());
      int[] P = new int[N]; //정답 배열
      int[] order = new int[N]; //순서 규칙 배열
      int[] cards = new int[N]; //현재 카드 배열
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      //정답 배열 세팅
      for(int i=0; i < N; i++) P[i] = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(br.readLine());
      for(int i=0; i < N; i++) {
         order[Integer.parseInt(st.nextToken())] = i;
         cards[i] = i%3;
      }
 
      int[] compare = cards.clone();
      int[] next = new int[N];
 
      int result = 0;
      while(!Arrays.equals(cards, P) && !(result !=0 && Arrays.equals(cards, compare))) {
         for(int j=0; j < N; j++) next[order[j]] = cards[j];
         
         cards = next.clone();
         result++;
      }
   
      if(result !=0 && Arrays.equals(cards, compare)) System.out.println(-1);
      else System.out.println(result);
      
      br.close();
   }
}