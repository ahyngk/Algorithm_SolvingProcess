import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1943_02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int repeat = 3;

        for (int tc = 0; tc < repeat; tc++) {
            List<Coin> coinList = new ArrayList<>();
            int totalPrice = 0;
            int coinKind = Integer.parseInt(br.readLine());
            for (int i = 0; i < coinKind; i++) {
                st = new StringTokenizer(br.readLine());
                coinList.add(new Coin(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
                totalPrice += coinList.get(coinList.size()-1).price * coinList.get(coinList.size()-1).count;
            }
            if(totalPrice%2 != 0){
                System.out.println(0);
                continue;
            }
            Collections.sort(coinList);

            boolean[] prices = new boolean[totalPrice/2+1];
            prices[0] = true;
            for (int i = 0; i < coinList.size(); i++) {
                for (int j = prices.length-1; j >= 0; j--) {
                    if(prices[j] && j + coinList.get(i).price < prices.length){
                        for (int k = 1; k <= coinList.get(i).count; k++) {
                            if(j + coinList.get(i).price * k < prices.length){
                                prices[j + coinList.get(i).price * k] = true;
                            }
                        }
                    }
                }
            }
            if(prices[prices.length-1]){
                System.out.println(1);
            }
            else {
                System.out.println(0);
            }
        }
    }

    static class Coin implements Comparable<Coin>{
        public Coin(){}
        public Coin(int price, int count) {
            this.price = price;
            this.count = count;
        }

        int price;
        int count;

        @Override
        public int compareTo(Coin coin){
            return coin.price - this.price;
        }
    }
}
