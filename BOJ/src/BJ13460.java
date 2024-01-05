import java.io.*;
import java.util.*;

public class BJ13460 {
    static int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
    static String[][] originalMap;
    static int[] redStart;
    static int[] blueStart;
    static boolean redEnd;
    static boolean blueEnd;
    static boolean finalCheck = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());

        originalMap = new String[rows][columns];
        for (int i = 0; i < originalMap.length; i++) {
            originalMap[i] = br.readLine().split("");
        }

        int[][] startCoor = findCoors();
        redStart = startCoor[0].clone();
        blueStart = startCoor[1].clone();

        int answer = -1;
        for (int i = 1; i < 11; i++) {
            selectDirections(0,new int[i]);
            if(finalCheck){
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }
    // Select each movement directions
    static void selectDirections(int index, int[] selectedDir){
        if(finalCheck){
            return;
        }
        if(index == selectedDir.length){
            String[][] map = cloneMap();
            redEnd = false;
            blueEnd = false;

            boolean current = move(map,selectedDir);
            if(current){
                finalCheck = true;
            }
            return;
        }
        for (int i = 0; i < directions.length; i++) {
            selectedDir[index] = i;
            selectDirections(index+1,selectedDir);
        }
    }
    // Move Control
    // Get marvel's priority with each direction
    // Move both marvels
    // return consequence as boolean
    static boolean move(String[][] map, int[] selectedDir){
        int[] red = redStart.clone();
        int[] blue = blueStart.clone();

        for (int i = 0; i < selectedDir.length; i++) {
            int priority = figurePriority(red,blue,selectedDir[i]);
            if(priority == 0){
                red = moveOne(red,selectedDir[i], map, 0);
                blue = moveOne(blue,selectedDir[i],map, 1);
                if(blueEnd){
                    return false;
                }
                if(redEnd){
                    return true;
                }
            }
            else {
                blue = moveOne(blue,selectedDir[i],map, 1);
                red = moveOne(red,selectedDir[i], map, 0);
                if(blueEnd){
                    return false;
                }
                if(redEnd){
                    return true;
                }
            }
        }
        return false;
    }
    // Figure which marvel to move first (according to direction)
    // return 0 if red has priority, return 1 if blue
    static int figurePriority(int[] red, int[] blue, int dir){
        switch (dir){
            case 0 :
                if(originalMap[0].length-red[1] < originalMap[0].length-blue[1]){
                    return 0;
                }
                return 1;
            case 1 :
                if(red[1] < blue[1]){
                    return 0;
                }
                return 1;
            case 2 :
                if(originalMap.length-red[0] < originalMap.length-blue[0]){
                    return 0;
                }
                return 1;
            case 3 :
                if(red[0] < blue[0]){
                    return 0;
                }
                return 1;
        }
        return -1;
    }
    // Move one marvel by selected direction
    // return its last coordinates
    static int[] moveOne(int[] marvle, int dir, String[][] map, int ball){
        int[] current = marvle.clone();
        boolean check = false;
        String first = map[marvle[0]][marvle[1]];

        while (true){
            // Next Coordinates
            int[] next = {current[0]+directions[dir][0], current[1]+directions[dir][1]};
            // Met map's end
            if(!map[next[0]][next[1]].equals(".")){
                check = true;
            }
            // If current ball went in
            if(map[next[0]][next[1]].equals("O")){
                map[current[0]][current[1]] = ".";
                if(ball == 0){
                    redEnd = true;
                }
                else {
                    blueEnd = true;
                }
                return next;
            }
            if(check){
                break;
            }
            // Map next Coordinates
            map[current[0]][current[1]] = ".";
            current = next.clone();
            map[current[0]][current[1]] = first;
        }
        return current;
    }
    static String[][] cloneMap(){
        String[][] map = new String[originalMap.length][originalMap[0].length];
        for (int i = 0; i < map.length; i++) {
            map[i] = originalMap[i].clone();
        }
        return map;
    }
    static void printMap(String[][] map){
        System.out.println("map--------------------------");
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
    static int[][] findCoors(){
        int[][] coors = new int[3][2];
        for (int i = 0; i < originalMap.length; i++) {
            for (int j = 0; j < originalMap[0].length; j++) {
                if(originalMap[i][j].equals("R")){
                    coors[0][0] = i;
                    coors[0][1] = j;
                }
                if(originalMap[i][j].equals("B")){
                    coors[1][0] = i;
                    coors[1][1] = j;
                }
                if(originalMap[i][j].equals("O")){
                    coors[2][0] = i;
                    coors[2][1] = j;
                }
            }
        }
        return coors;
    }
}
