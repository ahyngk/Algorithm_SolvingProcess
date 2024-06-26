package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BJ2668 {
    static boolean[] counts = new boolean[101];
    static int[] array;
    static Set<Integer> selectedMax = new HashSet<>();

    public static void main(String[] args) throws IOException {
        read();
        run();
        print();
    }
    /**
     * LOGIC
     * 둘째 줄의 숫자를 뽑거나 뽑지 않거나 2가지 경우
     * 완전탐색의 경우 2^100으로 시간 초과
     * 1안) 재귀를 이용한 탐색
     * 2째 줄에서 뽑힌 숫자가 있는 첫번째 줄의 숫자로 이동해 그 줄의 두번째 숫자를 선택
     * 이미 한번 뽑힌 수가 나온 경우 종료
     * 각 자리의 두번째 수를 처음 시작으로 했을 때, 중간에 다른 수를 고르지 않는 경우는 고려 필요 없다
     * 지속적으로 골라 나가다가 중복되면 멈추면 됨
     * 마지막에 맨 처음 고른 첫번째 줄의 수가 포함 되어 있는지 여부 확인 필요
     */
    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());
        array = new int[total+1];
        for (int i = 1; i < array.length; i++) {
            int temp = Integer.parseInt(br.readLine());
            array[i] = temp;
        }
    }
    static void run(){
        for (int i = 1; i < array.length; i++) {
            search(i,array[i],i,new ArrayList<>());
        }
    }
    static void search(int first, int second, int realFirst,List<Integer> selected){
        if(counts[second]){
            if(counts[realFirst]){
                for (int i = 0; i < selected.size(); i++) {
                    selectedMax.add(selected.get(i));
                }
            }
            return;
        }
        counts[second] = true;
        selected.add(second);
        search(second, array[second], realFirst, selected);
        counts[second] = false;
    }
    static void print(){
        StringBuilder sb = new StringBuilder();
        List<Integer> total = selectedMax.stream().collect(Collectors.toList());
        Collections.sort(total);
        sb.append(total.size()).append("\n");
        for (int i = 0; i < total.size(); i++) {
            sb.append(total.get(i)).append("\n");
        }
        System.out.println(sb);
    }
}
