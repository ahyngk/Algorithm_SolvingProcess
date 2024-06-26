package solved;

import java.util.*;
import java.io.*;

public class BJ15683 {
    static Map<Integer,List<int[]>[]> cctv;
    static int[][] map;
    static List<int[]> cctvCoors;
    static int minBlind = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        makeDirections();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());

        map = new int[rows][columns];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        findCCTV();
        int[] directions = new int[cctvCoors.size()];
        selectDirections(0,directions);
        System.out.println(minBlind);
    }
    static void selectDirections(int index, int[] directions){
        if(index == directions.length){
            int[][] tempMap = new int[map.length][map[0].length];
            for (int i = 0; i < tempMap.length; i++) {
                tempMap[i] = map[i].clone();
            }
            startWatch(tempMap, directions);
            return;
        }
        for (int i = 0; i < cctv.get(map[cctvCoors.get(index)[0]][cctvCoors.get(index)[1]]).length; i++) {
            directions[index] = i;
            selectDirections(index+1, directions);
        }
    }
    static void startWatch(int[][] tempMap, int[] directions){
        for (int i = 0; i < cctvCoors.size(); i++) {
            List<int[]> currentWatch = cctv.get(tempMap[cctvCoors.get(i)[0]][cctvCoors.get(i)[1]])[directions[i]];
            for (int j = 0; j < currentWatch.size(); j++) {
                int x = cctvCoors.get(i)[0] + currentWatch.get(j)[0];
                int y = cctvCoors.get(i)[1] + currentWatch.get(j)[1];
                while (true){
                    if(!borderCheck(x,y) || tempMap[x][y] == 6){
                        break;
                    }
                    if(tempMap[x][y] == 0){
                        tempMap[x][y] = 10;
                    }
                    x += currentWatch.get(j)[0];
                    y += currentWatch.get(j)[1];
                }
            }
        }
        countBlind(tempMap);
    }
    static void countBlind(int[][] tempMap){
        int count = 0;
        for (int i = 0; i < tempMap.length; i++) {
            for (int j = 0; j < tempMap[0].length; j++) {
                if(tempMap[i][j] == 0){
                    count++;
                }
            }
        }
        minBlind = Math.min(count,minBlind);
    }
    static void findCCTV(){
        cctvCoors = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j]>0 && map[i][j]!=6){
                    cctvCoors.add(new int[] {i,j});
                }
            }
        }
    }
    static boolean borderCheck(int x, int y){
        if(x>=0 && x<map.length && y>=0 && y<map[0].length){
            return true;
        }
        return false;
    }
    static void makeDirections(){
        cctv = new HashMap<>();
        List<int[]>[] one = new List[4];
        one[0] = new ArrayList<>(Arrays.asList(new int[] {0,1}));
        one[1] = new ArrayList<>(Arrays.asList(new int[] {1,0}));
        one[2] = new ArrayList<>(Arrays.asList(new int[] {0,-1}));
        one[3] = new ArrayList<>(Arrays.asList(new int[] {-1,0}));
        cctv.put(1,one);

        List<int[]>[] two = new List[2];
        two[0] = new ArrayList<>(Arrays.asList(new int[] {0,1}, new int[] {0,-1}));
        two[1] = new ArrayList<>(Arrays.asList(new int[] {1,0}, new int[] {-1,0}));
        cctv.put(2,two);

        List<int[]>[] three = new List[4];
        three[0] = new ArrayList<>(Arrays.asList(new int[] {0,1}, new int[] {-1,0}));
        three[1] = new ArrayList<>(Arrays.asList(new int[] {0,1}, new int[] {1,0}));
        three[2] = new ArrayList<>(Arrays.asList(new int[] {0,-1}, new int[] {1,0}));
        three[3] = new ArrayList<>(Arrays.asList(new int[] {0,-1}, new int[] {-1,0}));
        cctv.put(3,three);

        List<int[]>[] four = new List[4];
        four[0] = new ArrayList<>(Arrays.asList(new int[] {0,1}, new int[] {-1,0}, new int[] {0,-1}));
        four[1] = new ArrayList<>(Arrays.asList(new int[] {1,0}, new int[] {-1,0}, new int[] {0,1}));
        four[2] = new ArrayList<>(Arrays.asList(new int[] {0,1}, new int[] {1,0}, new int[] {0,-1}));
        four[3] = new ArrayList<>(Arrays.asList(new int[] {1,0}, new int[] {-1,0}, new int[] {0,-1}));
        cctv.put(4,four);

        List<int[]>[] five = new List[1];
        five[0] = new ArrayList<>(Arrays.asList(new int[] {0,1}, new int[] {0,-1}, new int[] {1,0}, new int[] {-1,0}));
        cctv.put(5,five);
    }
}
