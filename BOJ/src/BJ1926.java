import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1926 {
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());

        map = new int[rows][columns];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int count = 0;
        int max = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 1){
                    count++;
                    max = Math.max(run(i,j),max);
                }
            }
        }
        System.out.println(count);
        System.out.println(max);
    }
    static int run(int x, int y){
        map[x][y] = 2;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x,y});

        int count = 0;
        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            count++;

            for (int i = 0; i < directions.length; i++) {
                int nextX = temp[0] + directions[i][0];
                int nextY = temp[1] + directions[i][1];
                if(borderCheck(nextX,nextY) && map[nextX][nextY] == 1){
                    map[nextX][nextY] = 2;
                    queue.add(new int[] {nextX,nextY});
                }
            }
        }
        return count;
    }
    static boolean borderCheck(int x, int y){
        if(x>=0 && x< map.length && y>=0 && y<map[0].length){
            return true;
        }
        return false;
    }
}
