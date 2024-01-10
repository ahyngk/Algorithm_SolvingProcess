import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1261 {
    static int[][] map;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

    public static void main(String[] args) throws IOException{
        read();
        System.out.println(run());
    }
    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int columns = Integer.parseInt(st.nextToken());
        int rows = Integer.parseInt(st.nextToken());
        map = new int[rows][columns];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
    }

    static int run(){
        Queue<Algospot> queue = new LinkedList<>();
        queue.add(new Algospot(0,0,0));
        int[][] visitMap = makeMap();
        visitMap[0][0] = 0;

        while (!queue.isEmpty()){
            Algospot current = queue.poll();
            for (int i = 0; i < directions.length; i++) {
                Algospot next = new Algospot();
                next.x = current.x + directions[i][0];
                next.y = current.y + directions[i][1];
                if(borderCheck(next.x, next.y)){
                    next.brokeWalls = map[next.x][next.y] == 1 ? current.brokeWalls+1 : current.brokeWalls;
                    if(visitMap[next.x][next.y] > next.brokeWalls){
                        queue.add(next);
                        visitMap[next.x][next.y] = next.brokeWalls;
                    }
                }
            }
        }
        return visitMap[visitMap.length-1][visitMap[0].length-1];
    }
    static int[][] makeMap(){
        int[][] visitedMap = new int[map.length][map[0].length];
        for (int i = 0; i < visitedMap.length; i++) {
            Arrays.fill(visitedMap[i],Integer.MAX_VALUE);
        }
        return visitedMap;
    }
    static boolean borderCheck(int x, int y){
        if(x>=0 && x< map.length && y>=0 && y<map[0].length){
            return true;
        }
        return false;
    }
    static void printMap(int[][] map){
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static class Algospot{
        public Algospot() {
        }

        public Algospot(int x, int y, int brokeWalls) {
            this.x = x;
            this.y = y;
            this.brokeWalls = brokeWalls;
        }

        int x;
        int y;
        int brokeWalls;

        @Override
        public String toString() {
            return "Algospot{" +
                    "x=" + x +
                    ", y=" + y +
                    ", brokeWalls=" + brokeWalls +
                    '}';
        }
    }
}
