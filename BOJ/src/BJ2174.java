import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2174 {
    static Robot[][] map;
    static List<Command> commandList;
    public static void main(String[] args) throws IOException {
        read();
        printMap();
    }
    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rowSize = Integer.parseInt(st.nextToken());
        int columnSize = Integer.parseInt(st.nextToken());

        map = new Robot[rowSize][columnSize];
        st = new StringTokenizer(br.readLine());
        int robotSize = Integer.parseInt(st.nextToken());
        int commandSize = Integer.parseInt(st.nextToken());
        for (int i = 0; i < robotSize; i++) {
            st = new StringTokenizer(br.readLine());
            Robot robot = new Robot(i+1,Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, st.nextToken());
            map[robot.x][robot.y] = robot;
        }

        commandList = new ArrayList<>();
        for (int i = 0; i < commandSize; i++) {
            st = new StringTokenizer(br.readLine());
            commandList.add(new Command(Integer.parseInt(st.nextToken()), st.nextToken(),Integer.parseInt(st.nextToken())));
        }
    }
    static void run(){

    }
    static boolean commandLR(Robot robot, int direction){
        // Turn Left 90 degrees
        String[] directions = {"W", "N","E","S"};
        robot.direction = directions[direction];
        map[robot.x][robot.y] = robot;
        return true;
    }
    static boolean commandR(Robot robot, int direction){
        // Turn Right 90 degrees
        String[] directions = {"E","S","W","N"};
        robot.direction = directions[direction];
        map[robot.x][robot.y] = robot;
        return true;
    }
    static boolean commandF(Robot robot, int direction){
        int[][] directions = {{1,0},{0,-1},{0,1},{-1,0}};
        int nextX = robot.x + directions[direction][0];
        int nextY = robot.y + directions[direction][1];
        if(checkBorder(nextX,nextY)){
            return false;
        }
        map[robot.x][robot.y] = null;
        robot.x = nextX;
        robot.y = nextY;
        map[robot.x][robot.y] = robot;
        return true;
    }
    static boolean checkBorder(int x, int y){
        if(x>=0 && x< map.length && y>=0 && y<map[0].length){
            return true;
        }
        return false;
    }
    static void printMap(){
        for (int i = map.length-1; i >= 0; i--) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
    static class Robot {
        public Robot() {
        }

        public Robot(int robotNum, int x, int y, String direction) {
            this.robotNum = robotNum;
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
        int robotNum;
        int x;
        int y;
        String direction;

        @Override
        public String toString() {
            return "Robot{" +
                    "robotNum=" + robotNum +
                    ", x=" + x +
                    ", y=" + y +
                    ", direction='" + direction + '\'' +
                    '}';
        }
    }
    static class Command {
        public Command() {
        }

        public Command(int robotNum, String commandKind, int repeatSize) {
            this.robotNum = robotNum;
            this.commandKind = commandKind;
            this.repeatSize = repeatSize;
        }

        int robotNum;
        String commandKind;
        int repeatSize;

        @Override
        public String toString() {
            return "Command{" +
                    "robotNum=" + robotNum +
                    ", commandKind='" + commandKind + '\'' +
                    ", repeatSize=" + repeatSize +
                    '}';
        }
    }
}
