import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int ans;
    static int[][] games;
    static int[] score;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            games = new int[6][3]; // 팀별 승, 무, 패
            score = new int[6];

            for (int j = 0; j < 18; j++) {
                games[j/3][j%3] = Integer.parseInt(st.nextToken());
            }

            int t;
            boolean flag = true;
            for (int j = 0; j < 6; j++) {
                t = 0;
                for (int k = 0; k < 3; k++) t += games[j][k];
                if (t != 5) flag = false; // 5판 못채우면 = 불가능
            }

            if (!flag) {
                sb.append(0 + " ");
                continue; 
            } 

            ans = 0;
            check(1);
            sb.append(ans + " ");
        }
        System.out.print(sb.toString());
    }

    static void check(int cnt) {
        // 승부가 끝났다
        if (cnt == 6) {
            ans = 1;
            return;
        }

        for (int i = 0; i < 6; i++) {
            // 나랑 승부를 볼 수 없다
            if (cnt == i) continue;
            
            // 경기수를 다 채웠으면 
            if (score[i] == 5) continue;

            for (int j = 0; j < 3; j++) {
                // 승부 성사가 안되면 패스
                if (games[i][j] == 0 || games[cnt][2-j] == 0) continue;

                games[i][j]--;
                games[cnt][2-j]--;
                score[i]++;
                score[cnt]++;

                if (cnt < 5) check(cnt + 1); // 다시 원정
                else check(i + 2); // A-B, B-A 똑같은 경기 할 필요 X
                games[i][j]++;
                games[cnt][2-j]++;
                score[i]--;
                score[cnt]--;
            }

            return; // 모든 경기를 진행했으니 return
        }
    }
}