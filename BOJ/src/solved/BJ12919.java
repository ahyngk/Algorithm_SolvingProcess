package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BJ12919 {
    static String original;
    static String reverseOriginal;
    static List<String> toChange;
    static int maxChange = 0;
    static boolean check = false;
    public static void main(String[] args) throws IOException {
        read();
        select(0,new int[maxChange]);
        System.out.println(check == true ? 1: 0);
    }
    static void read() throws IOException {
        // A is 0, B is 1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        toChange = Arrays.stream(br.readLine().split("")).collect(Collectors.toList());
        original = br.readLine();
        reverseOriginal = new StringBuilder(original).reverse().toString();
        maxChange = original.length()-toChange.size();
    }

    static void select(int index, int[] selected){
        if(check){
            return;
        }
        if(index == selected.length){
            runSelected(selected);
            return;
        }
        for (int i = 0; i < 2; i++) {
            selected[index] = i;
            select(index+1, selected);
        }
    }
    static void runSelected(int[] selected){
        List<String> clone = new ArrayList<>(toChange);
        int currentIndex = clone.size();
        for (int i = 0; i < selected.length; i++) {
            switch (selected[i]){
                case 0 :
                    clone.add(currentIndex,"A");
                    if(currentIndex != 0){
                        currentIndex = clone.size();
                    }
                    break;
                case 1:
                    clone.add(currentIndex,"B");
                    currentIndex = reverse(currentIndex, clone);
                    break;
            }
        }
        checkSame(clone, currentIndex);
    }
    static int reverse(int currentIndex, List<String> list){
        return currentIndex == 0 ? list.size() : 0;
    }
    static void checkSame(List<String> list, int currentIndex){
        StringBuilder sb = new StringBuilder();
        for (String s : list){
            sb.append(s);
        }
        String fin = sb.toString();
        if(fin.equals(original)){
            check = true;
        }
        else if(fin.equals(reverseOriginal)) {
            check = true;
        }
    }
}
