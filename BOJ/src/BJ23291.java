import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ23291 {
    static int mapSize = 0;
    static int totalDiff = 0;
    static int[][] map;
    static List<Fish> fistList = new ArrayList<>();
    static List<List<Fish>> floating = new ArrayList<>();
    static int[][] directions = {{0,1},{1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        mapSize = Integer.parseInt(st.nextToken());
        totalDiff = Integer.parseInt(st.nextToken());

        map = makeNewMap(-1);
        map[map.length-1] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < map[0].length; i++) {
            fistList.add(new Fish(map.length-1,i,map[map.length-1][i]));
        }

        int count = 0;
        while (true){
            count++;
            addFish(findSmallestCoors());
            stackFirstFish();
            while (floatFishBowl()){
                rotateFishBowl();
            }
            mergeFish();
            flattenList();
            halfFloatFirst();
            halfFloatSecond();
            mergeFish();
            flattenList();

            map = makeNewMap(-1);
            for (int i = 0; i < map[0].length; i++) {
                map[map.length-1][i] = fistList.get(i).size;
                fistList.get(i).x = map.length-1;
                fistList.get(i).y = i;
            }

            if(difference()){
                break;
            }
        }
        System.out.println(count);
    }
    static List<Integer> findSmallestCoors(){
        List<Integer> smallest = new ArrayList<>();
        Collections.sort(fistList);
        for (int i = 0; i < fistList.size(); i++) {
            if(fistList.get(i).size == fistList.get(0).size){
                smallest.add(i);
            }
        }
        return smallest;
    }
    static void addFish(List<Integer> smallest){
        for (int i = 0; i < smallest.size(); i++) {
            fistList.get(smallest.get(i)).size += 1;
            map[fistList.get(smallest.get(i)).x][fistList.get(smallest.get(i)).y] += 1;
        }
    }
    static void stackFirstFish(){
        Collections.sort(fistList,compareByXY());
        map[fistList.get(0).x][fistList.get(0).y] = -1;
        fistList.get(0).x -= 1;
        fistList.get(0).y += 1;
        map[fistList.get(0).x][fistList.get(0).y] = fistList.get(0).size;
    }
    static boolean floatFishBowl(){
        Collections.sort(fistList,compareByXY());
        List<List<Fish>> floatingTemp = new ArrayList<>();

        int currentX = fistList.get(0).x;
        int currentIdx = 0;
        boolean endCheck = true;
        label : while (endCheck){
            List<Fish> eachStack = new ArrayList<>();
            for (int i = currentIdx; i < fistList.size(); i++) {
                if(currentX == fistList.get(i).x){
                    eachStack.add(fistList.get(i));
                    if(i == fistList.size()-1){
                        endCheck = false;
                    }
                }
                else {
                    currentIdx = i;
                    currentX = fistList.get(currentIdx).x;
                    if(i == fistList.size()-1){
                        endCheck = false;
                    }
                    break;
                }
            }
            floatingTemp.add(eachStack);
        }

        List<Fish> eachStack = new ArrayList<>();
        while (floatingTemp.get(floatingTemp.size()-1).size() != floatingTemp.get(floatingTemp.size()-2).size()){
            eachStack.add(0,floatingTemp.get(floatingTemp.size()-1).remove(floatingTemp.get(floatingTemp.size()-1).size()-1));
        }
        floatingTemp.add(eachStack);

        if(floatingTemp.size()-1 <= floatingTemp.get(floatingTemp.size()-1).size()){
            floating = floatingTemp;
            return true;
        }
        return false;
    }
    static void rotateFishBowl(){
        List<List<Fish>> rotated = new ArrayList<>();
        int x = floating.get(floating.size()-1).get(0).x-floating.size()+1;

        for (int j = 0; j < floating.get(0).size(); j++) {
            List<Fish> oneLine = new ArrayList<>();
            int y = floating.get(floating.size()-1).get(0).y;
            for (int i = floating.size()-2; i >= 0; i--) {
                floating.get(i).get(j).x = x;
                floating.get(i).get(j).y = y;
                oneLine.add(floating.get(i).get(j));
                y++;
            }
            x++;
            rotated.add(oneLine);
        }
        rotated.add(floating.get(floating.size()-1));
        floating = rotated;

        fistList = new ArrayList<>();
        for (int i = 0; i < rotated.size(); i++) {
            for (int j = 0; j < rotated.get(i).size(); j++) {
                fistList.add(rotated.get(i).get(j));
            }
        }
    }
    static void mergeFish(){
        int[][] tempMap = makeNewMap(0);
        for (int i = 0; i < floating.size(); i++) {
            for (int j = 0; j < floating.get(i).size(); j++) {
                int currentX = floating.get(i).get(j).x;
                int currentY = floating.get(i).get(j).y;
                for (int k = 0; k < directions.length; k++) {
                    int idxI = i+directions[k][0];
                    int idxJ = j+directions[k][1];
                    if(idxI>=0 && idxI<floating.size() && idxJ>=0 && idxJ<floating.get(i).size()){
                        int dif = Math.abs(floating.get(i).get(j).size - floating.get(idxI).get(idxJ).size)/5;
                        if(dif>0){
                            if(floating.get(i).get(j).size>floating.get(idxI).get(idxJ).size){
                                tempMap[floating.get(i).get(j).x][floating.get(i).get(j).y] -= dif;
                                tempMap[floating.get(idxI).get(idxJ).x][floating.get(idxI).get(idxJ).y] += dif;
                            }
                            else {
                                tempMap[floating.get(i).get(j).x][floating.get(i).get(j).y] += dif;
                                tempMap[floating.get(idxI).get(idxJ).x][floating.get(idxI).get(idxJ).y] -= dif;
                            }
                        }
                    }
                }
            }
        }
        map = tempMap;
        fistList = new ArrayList<>();
        for (int i = 0; i < floating.size(); i++) {
            for (int j = 0; j < floating.get(i).size(); j++) {
                map[floating.get(i).get(j).x][floating.get(i).get(j).y] += floating.get(i).get(j).size;
                floating.get(i).get(j).size = map[floating.get(i).get(j).x][floating.get(i).get(j).y];
                fistList.add(floating.get(i).get(j));
            }
        }
    }
    static void halfFloatSecond(){
        List<List<Fish>> temp = new ArrayList<>();
        for (int i = floating.size()-1; i >= 0; i--) {
            List<Fish> oneLine = new ArrayList<>();
            for (int j = floating.get(i).size()/2-1; j >= 0; j--) {
                oneLine.add(floating.get(i).get(j));
            }
            temp.add(oneLine);
        }
        for (int i = 0; i < floating.size(); i++) {
            List<Fish> oneLine = new ArrayList<>();
            for (int j = floating.get(i).size()/2; j < floating.get(i).size(); j++) {
                oneLine.add(floating.get(i).get(j));
            }
            temp.add(oneLine);
        }

        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < temp.get(i).size(); j++) {
                temp.get(i).get(j).x = i;
                temp.get(i).get(j).y = j;
            }
        }
        floating = temp;
    }

    static void halfFloatFirst(){
        floating = new ArrayList<>();
        List<Fish> a = new ArrayList<>();
        for (int i = fistList.size()/2-1; i >= 0; i--) {
            a.add(fistList.get(i));
        }
        List<Fish> b = new ArrayList<>();
        for (int i = fistList.size()/2; i < fistList.size(); i++) {
            b.add(fistList.get(i));
        }
        floating.add(a);
        floating.add(b);
    }
    static void flattenList(){
        fistList = new ArrayList<>();
        for (int j = 0; j < floating.get(floating.size()-1).size(); j++) {
            for (int i = floating.size()-1; i >= 0; i--) {
                if(floating.get(i).size() > j){
                    fistList.add(floating.get(i).get(j));
                }
            }
        }
    }
    static boolean difference(){
        List<Fish> temp = new ArrayList<>(fistList);
        Collections.sort(temp);
        if(temp.get(temp.size()-1).size - temp.get(0).size <= totalDiff){
            return true;
        }
        return false;
    }
    static Comparator<Fish> compareByXY(){
        Comparator<Fish> comparator = new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                if(o1.x == o2.x){
                    return o1.y - o2.y;
                }
                return o1.x - o2.x;
            }
        };
        return comparator;
    }
    static int[][] makeNewMap(int fill){
        int[][] newMap = new int[mapSize][mapSize];
        for (int i = 0; i < newMap.length; i++) {
            Arrays.fill(newMap[i],fill);
        }
        return newMap;
    }
    static void printMap(){
        System.out.println("current map---------------------------------");
        for (int mapPrint = 0; mapPrint < map.length; mapPrint++) {
            System.out.println(Arrays.toString(map[mapPrint]));
        }
    }
    static void printFloating(){
        System.out.println("floating--------------------------------");
        for (int i = 0; i < floating.size(); i++) {
            System.out.println(floating.get(i));
        }
    }
    static class Fish implements Comparable<Fish>{
        int x;
        int y;
        int size;

        public Fish(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "x=" + x +
                    ", y=" + y +
                    ", size=" + size +
                    '}';
        }

        @Override
        public int compareTo(Fish o) {
            return this.size-o.size;
        }
    }
}
