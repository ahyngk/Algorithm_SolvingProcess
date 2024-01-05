package Debug;

import java.util.*;
import java.io.*;

public class boj_18428 {
    static int N;
    static int[][] map = new int[N][N];
    static int[][] new_map = new int[N][N];
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static boolean watch = false;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                String temp = st.nextToken();
                if(temp.equals("T")) { // 선생일 때
                    map[i][j] = 1;
                } else if (temp.equals("S")) { // 학생일 때
                    map[i][j] = 2;
                }
            }
        }
        dfs(0);
        System.out.println("NO");
    }
    public static void dfs(int count) {
        if (count == 3) {
            if (check() && !watch) {
                System.out.println("YES");
                System.exit(0);
                watch = true;
            }
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 3;
                    dfs(count+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static boolean check() {
        Queue<int[]> q = new LinkedList<>();
        new_map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                new_map[i][j] = map[i][j];
                if (new_map[i][j] == 1) { // 선생일 때
                    q.add(new int[] {i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            System.out.println("teacher");
            System.out.println(Arrays.toString(temp));
//            System.out.println(Arrays.toString(temp));

            for (int i = 0; i < dc.length; i++) {
                int nr = temp[0];
                int nc = temp[1];
                while (true){
                    nr += dr[i];
                    nc += dc[i];
                    if (nr>=0 && nc>=0 && nr<N && nc<N) {
                        if (new_map[nr][nc] == 2) { // 학생일 경우
                            return false;
                        }
                        if (new_map[nr][nc] == 3) { // 장애물
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }
}