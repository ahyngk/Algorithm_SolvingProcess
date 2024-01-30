import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2169 {
    static int[][] map;
    static int[][][] price;
    static int[][] directions = {{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws IOException {

    }
    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        map = new int[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        price = new int[map.length][map[0].length][3];
        price[0][0][0] = map[0][0];
        price[0][0][1] = map[0][0];
        price[0][0][2] = map[0][0];
    }
    static void run(){

    }
    static void configureValue(int x, int y, int direction){
        if(checkBorder(x,y)){
            List<Integer> nextDirections = nextDirections(direction);
            for (int i = 0; i < nextDirections.size(); i++) {

            }
        }
    }
    static List<Integer> nextDirections(int direction){
        switch (direction){
            case 0 :
                return List.of(0,1);
            case 1:
                return List.of(0,1,2);
            case 2:
                return List.of(1,2);
        }
        return new ArrayList<>();
    }
    static boolean checkBorder(int x, int y){
        if(x>=0 && x< map.length && y>=0 && y<map[0].length){
            return true;
        }
        return false;
    }
}
