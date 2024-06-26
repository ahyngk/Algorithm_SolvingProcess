package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BJ11066 {
    static StringBuilder sb = new StringBuilder();
    static int smallest = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        read();
        System.out.println(sb);
    }
    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int repeat = Integer.parseInt(br.readLine());
        for (int i = 0; i < repeat; i++) {
            int pageLength = Integer.parseInt(br.readLine());
            int[] pages = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            run(pages);
            sb.append(smallest).append("\n");
            smallest = Integer.MAX_VALUE;
        }
    }
    static void run(int[] pages){
        List<Integer> pageList = Arrays.stream(pages).boxed().collect(Collectors.toList());
        configure(new Merged(pageList, 0));
    }
    static void configure(Merged merged){
//        System.out.println("merged = " + merged.price+" : "+merged.pages);
        if(merged.pages.size() == 1){
            smallest = Math.min(smallest, merged.price);
            return;
        }
        if(merged.price >= smallest){
            return;
        }
        for (int i = 0; i < merged.pages.size()-1; i++) {
            configure(addPages(i, merged));
        }
    }
    static Merged addPages(int startIndex, Merged merged){
        List<Integer> temp = new ArrayList<>();
        int total = merged.price;
        for (int i = 0; i < merged.pages.size(); i++) {
            if(i == startIndex){
                total = total + merged.pages.get(i) + merged.pages.get(i+1);
                temp.add(merged.pages.get(i) + merged.pages.get(i+1));
            }
            if(i != startIndex && i != startIndex+1){
                temp.add(merged.pages.get(i));
            }
        }
        return new Merged(temp, total);
    }

    static class Merged{
        List<Integer> pages;
        int price;

        public Merged() {
        }

        public Merged(List<Integer> pages, int price) {
            this.pages = pages;
            this.price = price;
        }
    }
}
