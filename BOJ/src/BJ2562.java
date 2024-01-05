import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[9];
        for (int i = 0; i < arr.length; i++) {
            int temp = Integer.parseInt(br.readLine());
            arr[i] = temp;
        }

        int max = arr[0];
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i]>max){
                maxIndex = i;
                max = arr[i];
            }
        }
        System.out.println(max);
        System.out.println(maxIndex+1);
    }
}
