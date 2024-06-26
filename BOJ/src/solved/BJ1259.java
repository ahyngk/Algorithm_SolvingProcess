package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1259 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String current = br.readLine();
            if(current.equals("0")){
                break;
            }

            int start = 0;
            int end = current.length()-1;
            String answer = "yes";

            while (start<end){
                if(current.charAt(start)!=current.charAt(end)){
                    answer = "no";
                    break;
                }
                start+=1;
                end-=1;
            }
            System.out.println(answer);
        }
    }
}
