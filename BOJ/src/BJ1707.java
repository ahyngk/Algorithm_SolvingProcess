import java.util.*;
import java.io.*;

public class BJ1707 {
    static List<Integer>[] graph;
    static int[] nodeVis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine());
            int nodes = Integer.parseInt(st.nextToken());
            int edges = Integer.parseInt(st.nextToken());

            graph = new List[nodes];
            nodeVis = new int[nodes];
            for (int j = 0; j < graph.length; j++) {
                graph[j] = new ArrayList<>();
            }

            for (int j = 0; j < edges; j++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken())-1;
                int node2 = Integer.parseInt(st.nextToken())-1;
                graph[node1].add(node2);
                graph[node2].add(node1);
            }

            if(seperate()){
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
    static boolean seperate(){
        for (int i = 0; i < graph.length; i++) {
            if(!search(i)){
                return false;
            }
        }
        return true;
    }
    static boolean search(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        nodeVis[start] = nodeVis[start] == 0? 1 : nodeVis[start];

        while (!queue.isEmpty()){
            int temp = queue.poll();
            for (int i = 0; i < graph[temp].size(); i++) {
                if(nodeVis[graph[temp].get(i)] == nodeVis[temp]){
                    return false;
                }
                else if(nodeVis[graph[temp].get(i)] == 0) {
                    nodeVis[graph[temp].get(i)] = nodeVis[temp]*-1;
                    queue.add(graph[temp].get(i));
                }
            }
        }
        return true;
    }
}
