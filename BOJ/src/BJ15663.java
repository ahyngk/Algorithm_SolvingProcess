import java.io.*;
import java.util.*;

public class BJ15663 {
    static int[] nums;
    static List<String> sequence = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(nums);

        int[] selected = new int[M];
        boolean[] check = new boolean[nums.length];
        selection(0,check,selected);

        for (int i = 0; i < sequence.size(); i++) {
            System.out.println(sequence.get(i));
        }
    }
    static void selection(int index, boolean[] check, int[] selected){
        if(index == selected.length){
            String seq = arrToString(selected);
            if(!sequence.contains(seq)){
                sequence.add(seq);
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(!check[i]){
                check[i] = true;
                selected[index] = nums[i];
                selection(index+1, check, selected);
                check[i] = false;
            }
        }
    }
    static String arrToString(int[] selected){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < selected.length; i++) {
            sb.append(selected[i]).append(" ");
        }
        return sb.toString();
    }
}
