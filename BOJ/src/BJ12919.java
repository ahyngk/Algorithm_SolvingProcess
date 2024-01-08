import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ12919 {
    static long original = 0;
    static long toChange = 0;
    public static void main(String[] args) throws IOException {
        read();
        System.out.println("original = " + original);
        System.out.println("toChange = " + toChange);
    }
    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        for (int i = origin.length()-1; i >= 0; i--) {
            if(origin.charAt(i) == 'B'){
                original += Math.pow(2,(origin.length()-1-i));
            }
        }
        String change = br.readLine();
        for (int i = change.length()-1; i >= 0; i--) {
            if(change.charAt(i) == 'B'){
                toChange += Math.pow(2,(change.length()-1-i));
            }
        }
    }


}
