import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1253 {
    static List<Number> numbersList = new ArrayList<>();
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        read();
        run();
        System.out.println(answer);
    }
    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()){
            numbersList.add(new Number(Long.parseLong(st.nextToken()), false));
        }
        Collections.sort(numbersList);
    }
    static void run(){
        for (int i = 0; i < numbersList.size(); i++) {
            for (int j = i+1; j < numbersList.size(); j++) {
                int check = checkNumber(0,numbersList.size()-1, (numbersList.size()-1)/2,numbersList.get(i).number+numbersList.get(j).number);
                if(check != -1 && check!=i && check!=j){
                    countNum(check, numbersList.get(i).number+numbersList.get(j).number);
                }
            }
        }
    }
    static int checkNumber(int start, int end, int mid, long target){
        if(numbersList.get(mid).number == target){
            return mid;
        }
        if(start == end){
            return -1;
        }
        if(numbersList.get(mid).number < target){
            return checkNumber(mid+1, end, (mid+1+end)/2, target);
        }
        else {
            return checkNumber(start, mid, (start+mid)/2, target);
        }
    }
    static void countNum(int index, long target){
        int plus = index;
        int minus = index-1;
        boolean plusCheck = false;
        boolean minusCheck = false;
        while (!plusCheck || !minusCheck){
            if(plus<numbersList.size() && numbersList.get(plus).number == target && !numbersList.get(plus).checked){
                numbersList.get(plus).checked = true;
                answer++;
            }
            else {
                plusCheck = true;
            }
            if(minus>=0 && numbersList.get(minus).number == target && !numbersList.get(minus).checked){
                numbersList.get(minus).checked = true;
                answer++;
            }
            else {
                minusCheck = true;
            }
            plus++;
            minus--;
        }
    }
    static class Number implements Comparable<Number>{
        public Number(long number, boolean checked) {
            this.number = number;
            this.checked = checked;
        }
        long number;
        boolean checked;
        @Override
        public int compareTo(Number number){
            return Long.compare(this.number, number.number);
        }

        @Override
        public String toString() {
            return Long.toString(number);
        }
    }
}
