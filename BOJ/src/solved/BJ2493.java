package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class BJ2493 {
    static long[] towers;
    static LazerRecord[] records;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        read();
        run();
        System.out.println(sb);
    }
    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        towers = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        records = new LazerRecord[towers.length];
    }
    static void run(){
        for (int i = 0; i < towers.length; i++) {
            findEachEnd(i);
        }
    }

    static void findEachEnd(int index){
        int lastIndex = -1;
        long lastHeight = -1;
        for (int i = index-1; i >= 0; i--) {
            if(towers[i] >= towers[index]){
                lastIndex = i;
                lastHeight = towers[i];
                break;
            }
            if(records[i].towerIndex != -1){
                i = records[i].towerIndex+1;
            }
        }
        records[index] = new LazerRecord(lastIndex, lastHeight);
        sb.append(lastIndex+1).append(" ");
    }

    static class LazerRecord {
        public LazerRecord(int towerIndex, long towerHeight) {
            this.towerIndex = towerIndex;
            this.towerHeight = towerHeight;
        }
        int towerIndex;
        long towerHeight;

        @Override
        public String toString() {
            return "LazerRecord{" +
                    "towerIndex=" + towerIndex +
                    ", towerHeight=" + towerHeight +
                    '}';
        }
    }
}
