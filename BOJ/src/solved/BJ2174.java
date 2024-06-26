package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2174 {
    static Robot[][] map;
    static List<Command> commandList;
    static List<Robot> robotList;
    public static void main(String[] args) throws IOException {
        read();
        System.out.println(run());
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
        robotList = new ArrayList<>();
        for (int i = 0; i < robotSize; i++) {
            st = new StringTokenizer(br.readLine());
            Robot robot = new Robot(i+1,Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, st.nextToken());
            map[robot.x][robot.y] = robot;
            robotList.add(robot);
        }

        commandList = new ArrayList<>();
        for (int i = 0; i < commandSize; i++) {
            st = new StringTokenizer(br.readLine());
            commandList.add(new Command(Integer.parseInt(st.nextToken())-1, st.nextToken(),Integer.parseInt(st.nextToken())));
        }
    }
    static String run(){
        for (int i = 0; i < commandList.size(); i++) {
            Robot robot = robotList.get(commandList.get(i).robotNum);
            switch (commandList.get(i).commandKind){
                case "L" :
                    commandL(robot,commandList.get(i).repeatSize);
                    break;
                case "R" :
                    commandR(robot,commandList.get(i).repeatSize);
                    break;
                case "F" :
                    String result = commandF(robot,commandList.get(i).repeatSize);
                    if(!result.equals("OK")){
                        return result;
                    }
                    break;
            }
        }
        return "OK";
    }
    static int configureDirection(String direction){
        switch (direction){
            case "N" : return 0;
            case "E" : return 1;
            case "S" : return 2;
            case "W" : return 3;
        }
        return -1;
    }
    static void commandL(Robot robot, int repeat){
        // Turn Left 90 degrees
        String[] directions = {"W", "N","E","S"};
        for (int i = 0; i < repeat; i++) {
            robot.direction = directions[configureDirection(robot.direction)];
        }
    }
    static void commandR(Robot robot, int repeat){
        // Turn Right 90 degrees
        String[] directions = {"E","S","W","N"};
        for (int i = 0; i < repeat; i++) {
            robot.direction = directions[configureDirection(robot.direction)];
        }
    }
    static String commandF(Robot robot, int repeat){
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
        for (int i = 0; i < repeat; i++) {
            int nextX = robot.x + directions[configureDirection(robot.direction)][0];
            int nextY = robot.y + directions[configureDirection(robot.direction)][1];
            if(!checkBorder(nextX,nextY)){
                return makePrintString(robot.robotNum);
            }
            if(map[nextX][nextY] != null){
                return makePrintString(robot.robotNum, map[nextX][nextY].robotNum);
            }
            map[robot.x][robot.y] = null;
            robot.x = nextX;
            robot.y = nextY;
            map[robot.x][robot.y] = robot;
        }
        return "OK";
    }
    static boolean checkBorder(int x, int y){
        if(x>=0 && x< map.length && y>=0 && y<map[0].length){
            return true;
        }
        return false;
    }
    static String makePrintString(int robotNum){
        return "Robot "+robotNum+" crashes into the wall";
    }
    static String makePrintString(int robot1, int robot2){
        return "Robot "+robot1+" crashes into robot "+robot2;
    }
    static void printMap(){
        for (int j = map[0].length-1; j >= 0; j--) {
            for (int i = 0; i < map.length; i++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
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
            return robotNum + direction;
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
