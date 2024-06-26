package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ22251 {
    static int[][] led;
    static int[][] ledNumbers;
    static int changeMax;
    static int count = 0;
    static int[] maxNumber;
    static int[] currentFloor;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        maxNumber = Arrays.stream(st.nextToken().split("")).mapToInt(Integer::parseInt).toArray();
        int digits = Integer.parseInt(st.nextToken());
        changeMax = Integer.parseInt(st.nextToken());
        String currentfloorNum = st.nextToken();
        currentFloor = new int[digits];
        int index = currentFloor.length-1;
        for (int i = currentfloorNum.length()-1; i >= 0; i--) {
            currentFloor[index] = currentfloorNum.charAt(i) - '0';
            index--;
        }
        init();
        selection(0,0,new int[currentFloor.length]);
        System.out.println(count);
    }
    static void selection(int index, int changeCount, int[] changed){
        // 각 자리를 바꾸는 경우와 바꾸지 않는 경우
        // End Points
        // 1. Reached End of number
        if(index == changed.length){
            if(!checkChanges(changed) && checkOver(changed)){
                System.out.println(Arrays.toString(changed));
                count++;
            }
            return;
        }

        // Make Change
        for (int i = 0; i < led.length; i++) {
            if(i!=currentFloor[index] && changeCount+ledNumbers[currentFloor[index]][i] <= changeMax) {
                if(index == 0 && i > maxNumber[index]){
                    continue;
                }
                changed[index] = i;
                selection(index+1, changeCount+ledNumbers[currentFloor[index]][i], changed);
            }
        }

        // Not Changed
        changed[index] = currentFloor[index];
        selection(index+1, changeCount, changed);
    }
    static boolean checkOver(int[] changed){
        int origin = makeNum(maxNumber);
        int change = makeNum(changed);
        if(origin < change || change == 0){
            return false;
        }
        return true;
    }
    static int makeNum(int[] array){
        int num = 0;
        for (int i = array.length-1; i >= 0; i--) {
            num += Math.pow(10,array.length-1-i)*array[i];
        }
        return num;
    }
    static boolean checkChanges(int[] changed){
        boolean check = true;
        for (int i = 0; i < changed.length; i++) {
            if(changed[i] != currentFloor[i]){
                check = false;
                break;
            }
        }
        return check;
    }
    static void init(){
        led = new int[10][7];
        led[0] = new int[] {1,1,1,0,1,1,1};
        led[1] = new int[] {0,0,1,0,0,1,0};
        led[2] = new int[] {1,0,1,1,1,0,1};
        led[3] = new int[] {1,0,1,1,0,1,1};
        led[4] = new int[] {0,1,1,1,0,1,0};
        led[5] = new int[] {1,1,0,1,0,1,1};
        led[6] = new int[] {1,1,0,1,1,1,1};
        led[7] = new int[] {1,0,1,0,0,1,0};
        led[8] = new int[] {1,1,1,1,1,1,1};
        led[9] = new int[] {1,1,1,1,0,1,1};
        countNum();
    }
    static void countNum(){
        ledNumbers = new int[10][10];
        for (int i = 0; i < ledNumbers.length; i++) {
            for (int j = 0; j < ledNumbers[0].length; j++) {
                int count = 0;
                for (int k = 0; k < led[i].length; k++) {
                    if(led[i][k] != led[j][k]){
                        count++;
                    }
                }
                ledNumbers[i][j] = count;
            }
        }
    }
}
