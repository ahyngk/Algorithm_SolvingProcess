package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ11559 {
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[][] map = new String[12][6];
        for (int i = 0; i < map.length; i++) {
            map[i] = br.readLine().split("");
        }
        System.out.println(run(map));
    }
    static int run(String[][] map){
        int count = 0;
        while (true){
            if(!explode(map)){
                break;
            }
            else {
                count++;
            }
            map = fall(map);
        }
        return count;
    }
    static boolean explode(String[][] map){
        List<Integer> toExplode = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int current = i*10+j;
                if(!map[i][j].equals(".") && !toExplode.contains(current)){
                    findExplode(i,j,map,toExplode);
                }
            }
        }
        if(toExplode.isEmpty()){
            return false;
        }

        for (int i = 0; i < toExplode.size(); i++) {
            int current = toExplode.get(i);
            int explodeI = current/10;
            int explodeJ = current - explodeI*10;
            map[explodeI][explodeJ] = ".";
        }
        return true;
    }
    static void findExplode(int i, int j, String[][] map, List<Integer> toExplode){
        List<int[]> temp = new ArrayList<>();
        temp.add(new int[] {i,j});
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i,j});
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[i][j] = true;

        while (!queue.isEmpty()){
            int[] current = queue.poll();
            for (int k = 0; k < directions.length; k++) {
                int nextI = current[0] + directions[k][0];
                int nextJ = current[1] + directions[k][1];
                if(borderCheck(nextI,nextJ,map) && !visited[nextI][nextJ] && map[nextI][nextJ].equals(map[i][j])){
                    temp.add(new int[] {nextI,nextJ});
                    visited[nextI][nextJ] = true;
                    queue.add(new int[] {nextI,nextJ});
                }
            }
        }
        if(temp.size() >= 4){
            for (int k = 0; k < temp.size(); k++) {
                toExplode.add(temp.get(k)[0]*10 + temp.get(k)[1]);
            }
        }
    }
    static String[][] fall(String[][] map){
        String[][] newMap = makeNewMap(map);
        for (int j = 0; j < map[0].length; j++) {
            int index = newMap.length-1;
            for (int i = map.length-1; i >= 0; i--) {
                if(!map[i][j].equals(".")){
                    newMap[index][j] = map[i][j];
                    index--;
                }
            }
        }
        return newMap;
    }
    static String[][] makeNewMap(String[][] map){
        String[][] newMap = new String[map.length][map[0].length];
        for (int i = 0; i < newMap.length; i++) {
            for (int j = 0; j < newMap[0].length; j++) {
                newMap[i][j] = ".";
            }
        }
        return newMap;
    }
    static boolean borderCheck(int i, int j, String[][] map){
        if(i>=0 && i< map.length && j>=0 && j<map[0].length){
            return true;
        }
        return false;
    }
    static void printMap(String[][] map){
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
