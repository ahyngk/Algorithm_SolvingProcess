import java.io.*;
import java.util.*;

public class BJ1167_unsolved {
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int nodes = Integer.parseInt(br.readLine());

        graph = new List[nodes+1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < graph.length; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            while (true){
                int node2 = Integer.parseInt(st.nextToken());
                if(node2 == -1){
                    break;
                }
                int dis = Integer.parseInt(st.nextToken());
                Edge edge = new Edge(node2,dis);
                graph[node1].add(edge);
            }
        }
        int max = 0;
        for (int i = 1; i < graph.length; i++) {
            max = Math.max(run(i),max);
        }
        System.out.println(max);
    }
    static void search(int node, int dis){

    }

    static int run(int start){
        Queue<Edge> queue = new LinkedList<>();
        Edge first = new Edge(start,0);
        queue.add(first);

        int[] visited = new int[graph.length];
        Arrays.fill(visited,Integer.MAX_VALUE);
        visited[start] = 0;

        while (!queue.isEmpty()){
            Edge current = queue.poll();
            for (int i = 0; i < graph[current.node].size(); i++) {
                Edge next = new Edge(graph[current.node].get(i).node, current.dis+graph[current.node].get(i).dis);
                if(next.node > start && visited[next.node] > next.dis){
                    visited[next.node] = next.dis;
                    queue.add(next);
                }
            }
        }
        int currrentMax = 0;
        Arrays.sort(visited);
        for (int i = visited.length-1; i >= 0; i--) {
            if(visited[i] != Integer.MAX_VALUE){
                currrentMax = visited[i];
                break;
            }
        }
        return currrentMax;
    }

    static class Edge{
        int node;
        int dis;

        public Edge() {
        }

        public Edge(int node, int dis) {
            this.node = node;
            this.dis = dis;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    ", dis=" + dis +
                    '}';
        }
    }
}
