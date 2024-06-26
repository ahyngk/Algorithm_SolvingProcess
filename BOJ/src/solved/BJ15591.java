package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ15591 {
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int videoSize = Integer.parseInt(st.nextToken());
        int questions = Integer.parseInt(st.nextToken());

        graph = new List[videoSize];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < videoSize-1; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken())-1;
            int last = Integer.parseInt(st.nextToken())-1;
            long weight = Long.parseLong(st.nextToken());
            graph[first].add(new Edge(first,last,weight));
            graph[last].add(new Edge(last,first,weight));
        }

        for (int i = 0; i < questions; i++) {
            st = new StringTokenizer(br.readLine());
            long max = Long.parseLong(st.nextToken());
            int video = Integer.parseInt(st.nextToken())-1;
            System.out.println(bfs(video,max));
        }
    }
    static int bfs(int start, long max){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, Long.MAX_VALUE));
        long[] mins = new long[graph.length];
        boolean[] visited = new boolean[graph.length];
        Arrays.fill(mins, Long.MAX_VALUE);
        mins[start] = 0;
        visited[start] = true;

        while (!queue.isEmpty()){
            Node current = queue.poll();
            for (int i = 0; i < graph[current.node].size(); i++) {
                long currentMin = current.min;
                if(graph[current.node].get(i).usado < currentMin){
                    currentMin = graph[current.node].get(i).usado;
                }
                if(!visited[graph[current.node].get(i).end]){
                    visited[graph[current.node].get(i).end] = true;
                    mins[graph[current.node].get(i).end] = currentMin;
                    queue.add(new Node(graph[current.node].get(i).end, currentMin));
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < mins.length; i++) {
            if(mins[i] >= max){
                answer++;
            }
        }
        return answer;
    }
    static class Node implements Comparable<Node>{
        int node;
        long min;

        public Node() {
        }

        public Node(int node, long min) {
            this.node = node;
            this.min = min;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.min,o.min);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "node=" + node +
                    ", min=" + min +
                    '}';
        }
    }
    static class Edge implements Comparable<Edge>{
        public Edge() {
        }
        public Edge(int start, int end, long usado) {
            this.start = start;
            this.end = end;
            this.usado = usado;
        }
        int start;
        int end;
        long usado;

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.usado, o.usado);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "start=" + start +
                    ", end=" + end +
                    ", usado=" + usado +
                    '}';
        }
    }
}
