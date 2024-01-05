import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BJ18428 {
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    static int[][] map;
    static List<int[]> students = new ArrayList<>();
    static List<int[]> empty = new ArrayList<>();
    static List<int[]> teachers = new ArrayList<>();
    static boolean endCheck = false;

    public static void main(String[] args) throws IOException{
        read();
        choose(0,new ArrayList<>());
        System.out.println(endCheck ? "YES" : "NO");
    }
    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        map = new int[size][size];
        for (int i = 0; i < map.length; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                switch (input[j]){
                    case "X" :
                        map[i][j] = 0;
                        empty.add(new int[] {i,j});
                        break;
                    case "S" :
                        map[i][j] = 1;
                        students.add(new int[] {i,j});
                        break;
                    case "T":
                        map[i][j] = 2;
                        teachers.add(new int[] {i,j});
                        break;
                }
            }
        }
    }
    static void choose(int index, List<int[]> selected){
        if(endCheck){
            return;
        }
        if(selected.size() == 3){
            runPossibleCheck(selected);
            return;
        }
        for (int i = index; i < empty.size(); i++) {
            selected.add(empty.get(i));
            choose(i+1, selected);
            selected.remove(selected.size()-1);
        }
    }
    static void runPossibleCheck(List<int[]> selected){
        int[][] newMap = makeNewMap();
        mapObstacle(newMap,selected);
        mapTeacherSight(newMap);
        if(safeCheck(newMap)){
            endCheck = true;
        }
    }
    static int[][] makeNewMap(){
        int[][] newMap = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            newMap[i] = map[i].clone();
        }
        return newMap;
    }
    static void mapObstacle(int[][] map, List<int[]> selected){
        for (int i = 0; i < selected.size(); i++) {
            map[selected.get(i)[0]][selected.get(i)[1]] = 5;
        }
    }
    static void mapTeacherSight(int[][] map){
        for (int i = 0; i < teachers.size(); i++) {
            for (int j = 0; j < directions.length; j++) {
                int[] currentTeacher = teachers.get(i).clone();
                while (true){
                    currentTeacher[0] += directions[j][0];
                    currentTeacher[1] += directions[j][1];
                    if(!borderCheck(currentTeacher) || map[currentTeacher[0]][currentTeacher[1]] == 5){
                        break;
                    }
                    map[currentTeacher[0]][currentTeacher[1]] = 2;
                }
            }
        }
    }
    static boolean safeCheck(int[][] map){
        for (int i = 0; i < students.size(); i++) {
            if(map[students.get(i)[0]][students.get(i)[1]] == 2){
                return false;
            }
        }
        return true;
    }
    static boolean borderCheck(int[] coors){
        if(coors[0] >= 0 && coors[0] < map.length && coors[1] >= 0 && coors[1] < map[0].length){
            return true;
        }
        return false;
    }
    static void printMap(int[][] map){
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
