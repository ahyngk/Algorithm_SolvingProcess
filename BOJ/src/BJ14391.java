import java.util.*;
import java.io.*;

public class BJ14391 {
    static int[][] originPaper;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());

        originPaper = new int[rows][columns];
        int[][] paper = new int[rows][columns];
        for (int i = 0; i < paper.length; i++) {
            originPaper[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        List<List<int[]>> cutted = new ArrayList<>();
        selections(0,0,paper);
        System.out.println(max);
    }
    static void selections(int x, int y, int[][] paper){
        if(x == paper.length){
            makeList(paper);
            return;
        }

        int nextX = x;
        int nextY = y+1;
        if(nextY == paper[0].length){
            nextX = x+1;
            nextY = 0;
        }

        // 0 is horizontal, 1 is vertical
        paper[x][y] = 1;
        selections(nextX,nextY,paper);
        paper[x][y] = 0;
        selections(nextX,nextY,paper);
    }
    static void makeList(int[][] paper){
        List<List<Integer>> nums = new ArrayList<>();

        List<Integer> horizontalList = new ArrayList<>();
        for (int i = 0; i < paper.length; i++) {
            for (int j = 0; j < paper[0].length; j++) {
                if(paper[i][j] == 0){
                    horizontalList.add(originPaper[i][j]);
                }
                if((paper[i][j] == 1 || j == paper[0].length-1) && !horizontalList.isEmpty()) {
                    nums.add(horizontalList);
                    horizontalList = new ArrayList<>();
                }
            }
        }

        List<Integer> verticalList = new ArrayList<>();
        for (int j = 0; j < paper[0].length; j++) {
            for (int i = 0; i < paper.length; i++) {
                if(paper[i][j] == 1){
                    verticalList.add(originPaper[i][j]);
                }
                if((paper[i][j] == 0 || i == paper.length-1) && !verticalList.isEmpty()) {
                    nums.add(verticalList);
                    verticalList = new ArrayList<>();
                }
            }
        }
        calculate(nums);
    }
    static void calculate(List<List<Integer>> nums){
        int total = 0;
        for (int i = 0; i < nums.size(); i++) {
            int temp = 0;
            for (int j = 0; j < nums.get(i).size(); j++) {
                temp = temp*10 + nums.get(i).get(j);
            }
            total+=temp;
        }
        max = Math.max(total,max);
    }
}
