package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ16946 {
    static int[][] map;
    static List<int[]> walls = new ArrayList<>();
    static List<int[]> blanks = new ArrayList<>();
    static Group[][] groups;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        read();
        mapAllCounts();
        getAnswers();
        System.out.println(sb);
    }

    // IDEA ------------------------------------------------------------------------------------------------------------
    // BFS 알고리즘을 통해 미리 0으로 연결 된 상황을 적어둔다 (00개 연속 된 개수)
    // 각 0에 대해 속한 집단의 번호도 입력한다
    // 벽의 경우 4방에 있는 0들에서 연결 된 개수를 더한다

    // INPUT -----------------------------------------------------------------------------------------------------------
    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());
        map = new int[rows][columns];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 1){
                    walls.add(new int[]{i,j});
                }
                else {
                    blanks.add(new int[]{i,j});
                }
            }
        }
    }
    // BFS Part --------------------------------------------------------------------------------------------------------
    static void mapAllCounts() {
        groups = new Group[map.length][map[0].length];
        int groupNo = 1;
        for (int i = 0; i < blanks.size(); i++) {
            if(map[blanks.get(i)[0]][blanks.get(i)[1]] == 0){
                eachCount(groupNo, blanks.get(i).clone());
                groupNo++;
            }
        }
    }

    static void eachCount(int groupNo, int[] start) {
        List<int[]> queueList = new ArrayList<>();
        map[start[0]][start[1]] = -1;
        queueList.add(start);
        int count = 1;
        int index = 0;
        while (index< queueList.size()) {
            int[] current = queueList.get(index).clone();
            for (int i = 0; i < directions.length; i++) {
                int[] next = {current[0] + directions[i][0], current[1] + directions[i][1]};
                if (borderCheck(next) && map[next[0]][next[1]] == 0) {
                    queueList.add(next);
                    map[next[0]][next[1]] = -1;
                    count++;
                }
            }
            index++;
        }
        mapEachCount(new Group(groupNo,count), queueList);
    }
    static void mapEachCount(Group currentGroup, List<int[]> list){
        for (int i = 0; i < list.size(); i++) {
            groups[list.get(i)[0]][list.get(i)[1]] = currentGroup;
        }
    }

    // Count Part ------------------------------------------------------------------------------------------------------
    static void getAnswers(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 1){
                    sb.append(getEachAnswer(new int[] {i,j})%10);
                }
                else {
                    sb.append("0");
                }
            }
            sb.append("\n");
        }
    }
    static int getEachAnswer(int[] coor){
        int count = 1;
        List<Integer> countedList = new ArrayList<>();
        for (int i = 0; i < directions.length; i++) {
            int[] next = {coor[0]+directions[i][0], coor[1]+directions[i][1]};
            if(borderCheck(next) && map[next[0]][next[1]] != 1){
                if(!countedList.contains(groups[next[0]][next[1]].groupNo)){
                    count += groups[next[0]][next[1]].groupTotalCnt;
                    countedList.add(groups[next[0]][next[1]].groupNo);
                }
            }
        }
        return count;
    }

    // Utils -----------------------------------------------------------------------------------------------------------
    static boolean borderCheck(int[] coors){
        if(coors[0]>=0 && coors[0]< map.length && coors[1]>=0 && coors[1]<map[0].length){
            return true;
        }
        return false;
    }

    static void printMap(){
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
    static void printGroupMap(){
        for (int i = 0; i < groups.length; i++) {
            System.out.println(Arrays.toString(groups[i]));
        }
    }

    static class Group {
        public Group() {
        }

        public Group(int groupNo, int groupTotalCnt) {
            this.groupNo = groupNo;
            this.groupTotalCnt = groupTotalCnt;
        }

        int groupNo;
        int groupTotalCnt;

        @Override
        public String toString() {
            return "Group{" +
                    "groupNo=" + groupNo +
                    ", groupTotalCnt=" + groupTotalCnt +
                    '}';
        }
    }
}
