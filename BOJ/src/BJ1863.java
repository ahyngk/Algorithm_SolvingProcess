import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ1863 {
    public static void main(String[] args) throws IOException {
        // 한 줄씩 읽으면서 현재 스택 맨 위에 쌓여있는 숫자보다 작은 숫자가 나오면 POP, 작은 수면 계속되게
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());
        int buildings = 0;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek() >= y){
                if(stack.peek()>y){
                    buildings++;
                }
                stack.pop();
            }
            if(y != 0){
                stack.add(y);
            }
        }
        System.out.println(buildings+stack.size());
    }
}
