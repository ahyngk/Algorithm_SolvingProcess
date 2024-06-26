package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BJ18427 {
    static List<List<Integer>> students;
    static long[] count;
    public static void main(String[] args) throws IOException {
        read();
        run();
        System.out.println(Arrays.toString(count));
    }
    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        students = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int studentSize = Integer.parseInt(st.nextToken());
        int maxBlockSize = Integer.parseInt(st.nextToken());
        int towerSize = Integer.parseInt(st.nextToken());
        count = new long[towerSize+1];
        for (int i = 0; i < studentSize; i++) {
            students.add( new ArrayList<>(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList())));
        }
    }

    static void run(){

    }
}
