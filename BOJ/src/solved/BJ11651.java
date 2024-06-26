package solved;

import java.io.*;
import java.util.*;

public class BJ11651 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] map = new long[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[i][0] = Long.parseLong(st.nextToken());
            map[i][1] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(map, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if (o1[1] == o2[1]) {
                    return Long.compare(o1[0], o2[0]);
                } else {
                    return Long.compare(o1[1], o2[1]);
                }
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            sb.append(map[i][0]).append(" ").append(map[i][1]).append("\n");
        }
        System.out.println(sb);
    }
}
