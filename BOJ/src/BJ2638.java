import java.util.*;
import java.io.*;

public class BJ2638 {
    static int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
    static List<int[]> cheeseCoors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());
        int[][] map = new int[rows][columns];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(run(map));
    }
    static int run (int[][] map){
        int time = 0;
        while (true){
            time++;
            mapAir(map);
            meltCheese(map);
            map = makeNewMap(map);
            if(cheeseCoors.isEmpty()){
                break;
            }
        }
        return time;
    }
    static void mapAir(int[][] map){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0,0});
        map[0][0] = -1;

        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[0][0] = true;

        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            for (int i = 0; i < directions.length; i++) {
                int nextX = temp[0]+directions[i][0];
                int nextY = temp[1]+directions[i][1];
                if(borderCheck(nextX,nextY,map) && !visited[nextX][nextY] && map[nextX][nextY] == 0){
                    map[nextX][nextY] = -1;
                    visited[nextX][nextY] = true;
                    queue.add(new int[] {nextX,nextY});
                }
            }
        }
    }
    static void meltCheese(int[][] map){
        cheeseCoors = new LinkedList<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 1 && !checkMelt(i,j,map)){
                        cheeseCoors.add(new int[] {i,j});
                }
            }
        }
    }
    static boolean checkMelt(int x, int y, int[][] map){
        int count = 0;
        for (int i = 0; i < directions.length; i++) {
            int nextX = x+directions[i][0];
            int nextY = y+directions[i][1];
            if(borderCheck(nextX,nextY,map) && map[nextX][nextY] == -1){
                count++;
            }
        }

        if(count>1){
            return true;
        }
        return false;
    }
    static int[][] makeNewMap(int[][] map){
        int[][] newMap = new int[map.length][map[0].length];
        for (int i = 0; i < cheeseCoors.size(); i++) {
            newMap[cheeseCoors.get(i)[0]][cheeseCoors.get(i)[1]] = 1;
        }
        return newMap;
    }
    static boolean borderCheck(int x, int y, int[][] map){
        if(x>=0 && x< map.length && y>=0 && y<map[0].length){
            return true;
        }
        return false;
    }
}
