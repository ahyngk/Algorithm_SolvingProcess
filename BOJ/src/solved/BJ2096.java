package solved;

import java.util.*;
import java.io.*;

public class BJ2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] origin = new int[size][3];
        for (int i = 0; i < origin.length; i++) {
            origin[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Step[][] dp = new Step[size][3];
        dp[0][0] = new Step(origin[0][0], origin[0][0]);
        dp[0][1] = new Step(origin[0][1], origin[0][1]);
        dp[0][2] = new Step(origin[0][2], origin[0][2]);

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = new Step();
            dp[i][0].big = Math.max(dp[i-1][0].big, dp[i-1][1].big)+origin[i][0];
            dp[i][0].small = Math.min(dp[i-1][0].small, dp[i-1][1].small)+origin[i][0];

            dp[i][1] = new Step();
            dp[i][1].big = Math.max(Math.max(dp[i-1][0].big, dp[i-1][1].big), dp[i-1][2].big)+origin[i][1];
            dp[i][1].small = Math.min(Math.min(dp[i-1][0].small, dp[i-1][1].small),dp[i-1][2].small)+origin[i][1];

            dp[i][2] = new Step();
            dp[i][2].big = Math.max(dp[i-1][1].big, dp[i-1][2].big)+origin[i][2];
            dp[i][2].small = Math.min(dp[i-1][1].small, dp[i-1][2].small)+origin[i][2];
        }

        System.out.print(Math.max(Math.max(dp[dp.length-1][0].big,dp[dp.length-1][1].big),dp[dp.length-1][2].big)+" ");
        System.out.print(Math.min(Math.min(dp[dp.length-1][0].small,dp[dp.length-1][1].small),dp[dp.length-1][2].small));
    }
    static class Step{
        int big;
        int small;

        public Step() {
        }

        public Step(int big, int small) {
            this.big = big;
            this.small = small;
        }

        @Override
        public String toString() {
            return "Step{" +
                    "big=" + big +
                    ", small=" + small +
                    '}';
        }
    }
}
