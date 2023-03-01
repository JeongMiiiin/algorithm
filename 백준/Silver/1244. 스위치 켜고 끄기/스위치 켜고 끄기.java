import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st; 
	
	public static void main(String args[]) throws Exception
    {
        int T = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] TList = new int[T];
        for(int i=0; i < T; i++){
            TList[i] = Integer.parseInt(st.nextToken());
        }
        
        int caseNum = Integer.parseInt(br.readLine());
        
        for(int j=0; j < caseNum; j++){
        	st = new StringTokenizer(br.readLine());
            int sex = Integer.parseInt(st.nextToken());
            int targetNum = Integer.parseInt(st.nextToken());
            
            if(sex == 1){
                //남자일 경우
                for(int z=1; z <= T / targetNum;z++){
                    TList[(z*targetNum) - 1] = -(TList[(z*targetNum) - 1] - 1);
                }
            } else {
                //여자일 경우
                TList[targetNum - 1] = -(TList[targetNum - 1] - 1);
                for(int z=1; z <= T / 2;z++){
                    if(targetNum - z < 1 || targetNum + z > T){
                        break;
                    }
                    
                    int compareNum1 = TList[targetNum - z - 1];
                    int compareNum2 = TList[targetNum + z - 1];
                    if(compareNum1 == compareNum2){
                        TList[(targetNum - 1) - z] = -(TList[(targetNum - 1) - z] - 1);
                        TList[(targetNum - 1) + z] = -(TList[(targetNum - 1) + z] - 1);
                    } else {
                        break;
                    }
                    
                }
            }
        }
        
        int cnt=0;
		for (int r = 0; r < T; r++) {
			if(cnt>0&&cnt%20==0) System.out.println();
			System.out.print(TList[r]+" ");
			cnt++;
		}
        
    }
}
