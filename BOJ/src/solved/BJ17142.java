package solved;

import java.io.*;
import java.util.*;

public class BJ17142 {
    static List<int[]> virus;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    static int smallest = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int maxVirus = Integer.parseInt(st.nextToken());
        int[][] map = new int[size][size];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        findVirus(map);
        selection(0,-1, new int[maxVirus], map);
        smallest = smallest == Integer.MAX_VALUE ? -1 : smallest;
        System.out.println(smallest);
    }
    static void selection(int index, int before, int[] selected, int[][] map){
        if(index == selected.length){
            spreadVirus(selected,makeMap(map));
            return;
        }
        for (int i = before+1; i < virus.size(); i++) {
            selected[index] = i;
            selection(index+1,i,selected, map);
        }
    }
    static void spreadVirus(int[] selected, int[][] map){
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < selected.length; i++) {
            queue.add(new int[] {virus.get(selected[i])[0],virus.get(selected[i])[1]});
            map[virus.get(selected[i])[0]][virus.get(selected[i])[1]] = 1;
        }

        int time = 0;
        while (!queue.isEmpty()){
            if(checkSpread(map)){
                smallest = Math.min(smallest,time);
                return;
            }
            time++;
            if(time>smallest){
                return;
            }
            Queue<int[]> queue1 = new LinkedList<>();
            while (!queue.isEmpty()){
                int[] temp = queue.poll();
                for (int i = 0; i < directions.length; i++) {
                    int[] next = {temp[0]+directions[i][0], temp[1]+directions[i][1]};
                    if(borderCheck(next,map) && map[next[0]][next[1]] != 1){
                        queue1.add(next);
                        map[next[0]][next[1]] = 1;
                    }
                }
            }
            queue = queue1;
        }
    }
    static void printMap(int[][] map){
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
    static boolean checkSpread(int[][] map){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }
    static boolean borderCheck(int[] coor, int[][] map){
        if(coor[0]>=0 && coor[0]<map.length && coor[1]>=0 && coor[1]<map[0].length){
            return true;
        }
        return false;
    }
    static void findVirus(int[][] map){
        virus = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 2){
                    virus.add(new int[] {i,j});
                }
            }
        }
    }
    static int[][] makeMap(int[][] map){
        int[][] newMap = new int[map.length][map[0].length];
        for (int i = 0; i < newMap.length; i++) {
            newMap[i] = map[i].clone();
        }
        return newMap;
    }
}
