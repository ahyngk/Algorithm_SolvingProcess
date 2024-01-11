import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1943 {
    static StringBuilder answer = new StringBuilder();
    static int totalPrice;
    static boolean isPossible = false;

    public static void main(String[] args) throws IOException {
        run();
        System.out.println(answer);
    }
    static void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int runtime = 0; runtime < 3; runtime++) {
            List<Money> totalMoney = new ArrayList<>();
            isPossible = false;
            totalPrice = 0;

            int kind = Integer.parseInt(br.readLine());
            for (int i = 0; i < kind; i++) {
                st = new StringTokenizer(br.readLine());
                totalMoney.add(new Money(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
                totalPrice += totalMoney.get(totalMoney.size()-1).price;
            }
            Collections.sort(totalMoney);
            select(0,0,totalMoney);
            if(isPossible){
                answer.append(1).append("\n");
            }
            else {
                answer.append(0).append("\n");
            }
        }
    }
    static void select(int index, int currentTotal, List<Money> totalMoney){
        if(isPossible || index == totalMoney.size()){
            return;
        }
        if(currentTotal == totalPrice/2){
            isPossible = true;
            return;
        }
        if(currentTotal > totalPrice/2){
            return;
        }

        for (int i = totalMoney.get(index).count; i >=0 ; i--) {
            int toAdd = totalMoney.get(index).price*i;
            select(index+1, toAdd + currentTotal, totalMoney);
        }
    }

    static class Money implements Comparable<Money>{
        public Money() {
        }
        public Money(int price, int count) {
            this.price = price;
            this.count = count;
        }

        int price;
        int count;

        @Override
        public int compareTo(Money o) {
            return o.price - this.price;
        }

        @Override
        public String toString() {
            return "Money{" +
                    "price=" + price +
                    ", count=" + count +
                    '}';
        }
    }
}
