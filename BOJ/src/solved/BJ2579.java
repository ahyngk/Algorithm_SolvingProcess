package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        int[][] dp = new int[size][4];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = Integer.parseInt(br.readLine());
        }

        // 현재칸
        // 두칸 전에서 2칸 뛰기
        // 한칸 전에서 1칸 뛰기
        // 한칸 전 칸에 도달할 때 두칸 전에서 왔는지 한칸 전에서 왔는지

    }
}
