package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1943_01 {
    public static void main(String[] args) throws IOException{
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

            int answer = 0;
            int currentSum = 0;
            for (int i = 0; i < coinList.size(); i++) {
                if(currentSum == totalPrice/2){
                    answer = 1;
                    break;
                }
                int currentMaxCoin = (totalPrice/2 - currentSum)/coinList.get(i).price;
                currentSum += coinList.get(i).price * Math.min(currentMaxCoin, coinList.get(i).count);
            }
            System.out.println(answer);
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
