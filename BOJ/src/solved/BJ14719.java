package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        int[] blocks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Stack<int[]> temp = new Stack<>();
        int[] maxHeight = new int[] {0,blocks[0]};
        temp.add(maxHeight);
        int sum = 0;

        for (int i = 1; i < blocks.length; i++) {
            int[] current = new int[] {i,blocks[i]};

            if(maxHeight[1] <= current[1]){
                while (!temp.isEmpty()){
                    sum+=maxHeight[1] - temp.pop()[1];
                }
                maxHeight = current;
                temp.add(current);
            }
            else {
                temp.add(current);
            }

            if(i == blocks.length-1){
                int[] max = temp.pop();
                while (!temp.isEmpty()){
                    int[] temp1 = temp.pop();
                    if(temp1[1] - max[1] >=0){
                        max = temp1;
                    }
                    else {
                        sum+=max[1] - temp1[1];
                    }
                }
            }
        }
        System.out.println(sum);
    }
}
