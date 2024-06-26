package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11048 {
    static int[][] directions = {{0,-1},{-1,0}};
    static int[][] maze;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());

        maze = new int[rows][columns];
        for (int i = 0; i < maze.length; i++) {
            maze[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] dp = new int[maze.length][maze[0].length];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                int temp = maze[i][j];
                for (int k = 0; k < directions.length; k++) {
                    int x = i+directions[k][0];
                    int y = j+directions[k][1];
                    if(borderCheck(x,y)){
                        temp+=maze[x][y];
                    }
                }
                dp[i][j] = temp;
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }


        System.out.println(dp[dp.length-1][dp[0].length-1]);
    }
    static boolean borderCheck(int x, int y){
        if(x>=0 && x< maze.length && y>=0 && y< maze[0].length){
            return true;
        }
        return false;
    }
}
