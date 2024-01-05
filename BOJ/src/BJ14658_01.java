import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ14658_01 {
    static List<Integer>[] map;
    static int tSize;
    static int column;
    static int max = Integer.MIN_VALUE;
    static List<Integer> xarr;
    static List<Integer> yarr;
    static int[] xContains;
    static int[] yContains;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        column = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        tSize = Integer.parseInt(st.nextToken());
        int stars = Integer.parseInt(st.nextToken());

        map = new List[row+1];
        xContains = new int[row+1];
        yContains = new int[column+1];
        xarr = new ArrayList<>();
        yarr = new ArrayList<>();
        for (int i = 0; i < stars; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            if(map[x] == null){
                map[x] = new ArrayList<>();
            }
            map[x].add(y);
            if(xContains[x] == 0){
                xarr.add(x);
            }
            if(yContains[y] == 0){
                yarr.add(y);
            }
            xContains[x]++;
            yContains[y]++;
        }
        Collections.sort(xarr);
        Collections.sort(yarr);
        select();
        System.out.println(stars-max);
    }
    static void select(){
        for (int i = 0; i < xarr.size(); i++) {
            for (int j = 0; j < yarr.size(); j++) {
                int current = count(i,yarr.get(j), xarr.get(i)+tSize,yarr.get(j)+tSize);
                max = Math.max(current,max);
            }
        }
    }
    static int count(int x1, int y1, int x2, int y2){
        int count = 0;
        for (int i = x1; i < xarr.size(); i++) {
            if(xarr.get(i) <= x2){
                for (int j = 0; j < map[xarr.get(i)].size(); j++) {
                    if(map[xarr.get(i)].get(j) >= y1 && map[xarr.get(i)].get(j) <= y2){
                        count++;
                    }
                }
            }
            else {
                break;
            }
        }
        return count;
    }
}
