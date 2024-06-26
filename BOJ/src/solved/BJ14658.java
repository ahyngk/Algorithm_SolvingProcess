package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ14658 {
    static List<Integer>[] map;
    static int tSize;
    static int column;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        column = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        tSize = Integer.parseInt(st.nextToken());
        int stars = Integer.parseInt(st.nextToken());

        map = new List[row+1];
        for (int i = 0; i < stars; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            if(map[x] == null){
                map[x] = new ArrayList<>();
            }
            map[x].add(y);
        }

        for (int i = 0; i < map.length; i++) {
            if(map[i] == null){
                map[i] = new ArrayList<>();
            }
            Collections.sort(map[i]);
        }

        find();
        System.out.println(stars-max);
    }
    static void find(){
        int minI = Integer.MAX_VALUE;
        int minJ = Integer.MAX_VALUE;
        int maxI = Integer.MIN_VALUE;
        int maxJ = Integer.MIN_VALUE;
        for (int i = 0; i < map.length; i++) {
            if(!map[i].isEmpty()){
                if(minI == Integer.MAX_VALUE){
                    minI = i;
                }
                minJ = Math.min(map[i].get(0), minJ);
                maxJ = Math.max(map[i].get(map[i].size()-1), maxJ);
                maxI = Math.max(maxI,i);
            }
        }
        maxI = Math.max(Math.min(map.length-tSize-1, maxI), minI);
        maxJ = Math.max(Math.min(column-tSize-1, maxJ),minJ);
        select(minI,minJ,maxI,maxJ);
    }
    static void select(int minI, int minJ, int maxI, int maxJ){
        for (int i = minI; i <= maxI; i++) {
            for (int j = minJ; j <= maxJ; j++) {
                int current = count(i,j, i+tSize,j+tSize);
                max = Math.max(current,max);
            }
        }
    }
    static int count(int x1, int y1, int x2, int y2){
        int count = 0;
        for (int i = x1; i <= Math.min(x2,map.length-1); i++) {
            for (int j = 0; j < map[i].size(); j++) {
                if(map[i].get(j) >= y1 && map[i].get(j) <= y2){
                    count++;
                }
                if(map[i].get(j) > y2){
                    break;
                }
            }
        }
        return count;
    }
}
