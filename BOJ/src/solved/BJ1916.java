package solved;

import java.io.*;
import java.util.*;

public class BJ1916 {
    static List<int[]>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(br.readLine());
        int edges = Integer.parseInt(br.readLine());

        graph = new List[nodes+1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            graph[start].add(new int[] {end,price});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());
        System.out.println(run(start,destination));
    }
    static int run(int start, int destination){
        int[] distances = new int[graph.length];
        Arrays.fill(distances,Integer.MAX_VALUE);
        distances[start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[1] - o2[1];
                    }
                }
        );
        queue.add(new int[] {start,0});

        while (!queue.isEmpty()){
            int[] current = queue.poll();
            if(current[0] == destination){
                break;
            }
            for (int i = 0; i < graph[current[0]].size(); i++) {
                int[] next = {graph[current[0]].get(i)[0], current[1]+graph[current[0]].get(i)[1]};
                if(distances[next[0]] > next[1]){
                    distances[next[0]] = next[1];
                    queue.add(next);
                }
            }
        }
        return distances[destination];
    }
}
