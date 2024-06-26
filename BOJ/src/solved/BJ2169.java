package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2169 {
    static int[][] map;
    static boolean[][] visited;
    static int[][][] price;
    static int[][] directions = {{0,-1},{-1,0},{0,1}};
    public static void main(String[] args) throws IOException {
        read();
        run();
    }
    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        map = new int[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        visited = new boolean[map.length][map[0].length];
        price = new int[map.length][map[0].length][3];
        for (int i = 0; i < price.length; i++) {
            for (int j = 0; j < price[0].length; j++) {
                Arrays.fill(price[i][j], -(int) 2e9);
            }
        }
        price[0][0][0] = map[0][0];
        price[0][0][1] = map[0][0];
    }
    static void run(){
        int up = getLargestValue(map.length-1, map[0].length-1,0,0, 1);
        int left = getLargestValue(map.length-1, map[0].length-1,0,0, 2);
        System.out.println(Math.max(up, left));
    }
    static int getLargestValue(int x, int y, int beforeX, int beforeY, int direction){
        visited[x][y] = true;
//        printVisited();
        if(price[x][y][direction] != -(int) 2e9){
            visited[x][y] = false;
            return price[x][y][direction];
        }
        int left = -(int) 2e9;
        int right = -(int) 2e9;
        int top = -(int) 2e9;
        if(checkBorder(x+directions[0][0], y+directions[0][1]) && !visited[x+directions[0][0]][y+directions[0][1]] && (x+directions[0][0] != beforeX || y+directions[0][1] != beforeY)){
            left = getLargestValue(x+directions[0][0], y+directions[0][1], x, y, 0)+map[x][y];
        }
        if(checkBorder(x+directions[1][0], y+directions[1][1]) && !visited[x+directions[1][0]][y+directions[1][1]] && (x+directions[1][0] != beforeX || y+directions[1][1] != beforeY)){
            top = getLargestValue(x+directions[1][0], y+directions[1][1], x, y, 1)+map[x][y];
        }
        if(checkBorder(x+directions[2][0], y+directions[2][1]) && !visited[x+directions[2][0]][y+directions[2][1]] && (x+directions[2][0] != beforeX || y+directions[2][1] != beforeY)){
            right = getLargestValue(x+directions[2][0], y+directions[2][1], x, y, 2)+map[x][y];
        }
        price[x][y][direction] = Math.max(left, Math.max(right, top));
        visited[x][y] = false;
        return price[x][y][direction];
    }
    static void printVisited(){
        for (int i = 0; i < visited.length; i++) {
            System.out.println(Arrays.toString(visited [i]));
        }
        System.out.println();
    }
    static boolean checkBorder(int x, int y){
        if(x>=0 && x< map.length && y>=0 && y<map[0].length){
            return true;
        }
        return false;
    }
    static void printMap(){
        for (int i = 0; i < price.length; i++) {
            for (int l = 0; l < price[0][0].length; l++) {
                for (int j = 0; j < price[0].length; j++) {
                    System.out.print(price[i][j][l]+" ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
