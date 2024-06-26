package solved;

import java.util.*;

public class BJ13549 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int last = sc.nextInt();
        int total = 0;

        int[] check = new int[200001];
        Arrays.fill(check,200000);
        check[start] = 0;
        int[] startPoint = {start,0};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(startPoint);

        while (!queue.isEmpty()){
            int[] currentPoint = queue.poll();

            if(currentPoint[0] == last){
                total = currentPoint[1];
                continue;
            }

            if(currentPoint[0]+1>=0 && currentPoint[0]+1< check.length && check[currentPoint[0]+1]>currentPoint[1]+1){
                check[currentPoint[0]+1] = currentPoint[1]+1;
                queue.add(new int[] {currentPoint[0]+1, currentPoint[1]+1});
            }
            if(currentPoint[0]-1>=0 && currentPoint[0]-1< check.length && check[currentPoint[0]-1]>currentPoint[1]+1){
                check[currentPoint[0]-1] = currentPoint[1]+1;
                queue.add(new int[] {currentPoint[0]-1, currentPoint[1]+1});
            }
            if(currentPoint[0]*2>=0 && currentPoint[0]*2< check.length && check[currentPoint[0]*2]>currentPoint[1]){
                check[currentPoint[0]*2] = currentPoint[1];
                queue.add(new int[] {currentPoint[0]*2, currentPoint[1]});
            }
        }
        System.out.println(total);
    }
}