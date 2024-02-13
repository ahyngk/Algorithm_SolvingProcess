import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2668 {
    static int[] counts = new int[101];
    static int[] array;

    public static void main(String[] args) throws IOException {
        read();
        System.out.println(Arrays.toString(array));
    }
    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());
        array = new int[total+1];
        for (int i = 1; i < array.length; i++) {
            int temp = Integer.parseInt(br.readLine());
            array[i] = temp;
        }
    }
}
