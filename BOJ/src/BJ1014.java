import java.util.*;
import java.io.*;

public class BJ1014 {
    static int[][] blockedCoors = {{0,1},{0,-1},{1,1},{-1,-1}};
    static String [][] originalMap;
    static int maxStudents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Integer testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            maxStudents = Integer.MIN_VALUE;
            st = new StringTokenizer(br.readLine());
            int rows = Integer.parseInt(st.nextToken());
            int columns = Integer.parseInt(st.nextToken());

            originalMap = new String[rows][columns];
            int[][] map = new int[rows][columns];
            for (int i = 0; i < map.length; i++) {
                originalMap[i] = br.readLine().split("");
                for (int j = 0; j < originalMap[i].length; j++) {
                    int k = originalMap[i][j].equals(".")? 0 : 1;
                    map[i][j] = k;
                }
            }
            selections(map.length-1, map[0].length-1, map, 0);
            System.out.println(maxStudents);
        }
    }
    static void selections(int x, int y, int[][] map, int count){
        if(x<0){
            maxStudents = Math.max(count,maxStudents); //
            return;
        }

        int nextX = x;
        int nextY = y-1;
        if(nextY<0){
            nextX-=1;
            nextY = map[0].length-1;
        }

        if(map[x][y] == 0){
            map[x][y] += 1;
            mapSeats(x,y,map,1);
            selections(nextX,nextY,map,count+1);
            mapSeats(x,y,map,-1);
            map[x][y] -= 1;
        }
        selections(nextX,nextY,map,count);
    }
    static void mapSeats(int x, int y, int[][] map, int num){
        for (int i = 0; i < blockedCoors.length; i++) {
            int blockedX = x+blockedCoors[i][0];
            int blockedY = y+blockedCoors[i][1];
            if(borderCheck(blockedX,blockedY)){
                map[blockedX][blockedY] += num;
            }
        }
    }
    static boolean borderCheck(int x, int y){
        if(x>=0 && x< originalMap.length && y>=0 && y<originalMap[0].length){
            return true;
        }
        return false;
    }
}
