package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1987 {
    static char[][] board;
    static int max = Integer.MIN_VALUE;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());

        board = new char[rows][columns];
        for (int i = 0; i < board.length; i++) {
            board[i] = br.readLine().toCharArray();
        }
        boolean[] check = new boolean['Z' - 'A' + 1];
        check[board[0][0]-'A'] = true;
        move(0,0,1,check);
        System.out.println(max);
    }

    static void move(int x, int y, int count, boolean[] check) {
        max = Math.max(max,count);
        for (int i = 0; i < directions.length; i++) {
            int nextX = x+directions[i][0];
            int nextY = y+directions[i][1];
            if(borderCheck(nextX,nextY) && !check[board[nextX][nextY]-'A']){
                check[board[nextX][nextY]-'A'] = true;
                move(nextX,nextY,count+1,check);
                check[board[nextX][nextY]-'A'] = false;
            }
        }
    }
    static boolean borderCheck(int x, int y){
        if(x>=0 && x< board.length && y>=0 && y<board[0].length){
            return true;
        }
        return false;
    }
}