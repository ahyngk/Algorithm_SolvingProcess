import java.util.*;
import java.io.*;

public class BJ2138 {
    static int[] original;
    static int[] toMake;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        read();
        select(0,original.clone(), 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
    static void read() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        original = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        toMake = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
    }
    static void select(int index, int[] changedBulbs, int changed) {
        if(index == changedBulbs.length){
            boolean temp = true;
            for (int i = 0; i < changedBulbs.length; i++) {
                if(changedBulbs[i] != toMake[i]){
                    temp = false;
                    break;
                }
            }
            if(temp){
                answer = Math.min(changed,answer);
            }
            return;
        }
        if(index>=2){
            for (int i = index-2; i >= 0; i--) {
                if(changedBulbs[i] != toMake[i]){
                    return;
                }
            }
        }
        select(index+1, changedBulbs, changed);
        int[] change = {index-1, index, index+1};
        for (int i = 0; i < change.length; i++) {
            if(change[i] >=0 && change[i] < changedBulbs.length){
                changedBulbs[change[i]] = changedBulbs[change[i]] == 1 ? 0:1;
            }
        }
        select(index+1, changedBulbs, changed+1);
        for (int i = 0; i < change.length; i++) {
            if(change[i] >=0 && change[i] < changedBulbs.length){
                changedBulbs[change[i]] = changedBulbs[change[i]] == 1 ? 0:1;
            }
        }
    }
}
