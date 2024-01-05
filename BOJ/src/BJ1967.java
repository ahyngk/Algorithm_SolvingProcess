import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1967 {
    // 초기 접근은 root를 찾아서 root -> leaf로 가는 거리를 모두 저장
    // 그 이후 가장 긴 경로 2개를 찾아서 더하는 방식으로 생각
    // 하지만 root 이후 같은 노드를 통해 leaf로 도달했을 가능성 존재

    // root를 찾은 경우, root에서 가장 긴 거리를 지닌 노드를 찾은 뒤
    // 그 노드에서 가장 멀리 떨어진 노드까지의 거리를 찾으면 지름이 나옴
    static List<Edge>[] nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int nodeSize = Integer.parseInt(br.readLine());

        nodes = new List[nodeSize+1];
        int[] parents = new int[nodeSize+1];
        for (int i = 0; i < nodeSize-1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            if(nodes[parent] == null){
                nodes[parent] = new ArrayList<>();
            }
            if(nodes[child] == null){
                nodes[child] = new ArrayList<>();
            }
            nodes[parent].add(new Edge(child,dis));
            nodes[child].add(new Edge(parent,dis));
            parents[child] = parent;
        }
        int[] fromRoot = findEnd(findRoot(parents));
        int end = findMax(fromRoot);
        int[] length = findEnd(end);
        Arrays.sort(length);
        System.out.println(length[length.length-1]);
    }
    static int[] findEnd(int root){
        int[] distances = new int[nodes.length];
        Arrays.fill(distances,-1);
        distances[root] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {root,0});

        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            if(nodes[temp[0]] != null){
                for (int i = 0; i < nodes[temp[0]].size(); i++) {
                    int[] next = {nodes[temp[0]].get(i).child, temp[1]+nodes[temp[0]].get(i).distance};
                    if(distances[next[0]] == -1){
                        queue.add(next);
                        distances[next[0]] = next[1];
                    }
                }
            }
        }
        return distances;
    }
    static int findMax(int[] distances){
        int max = 0;
        int node = 0;
        for (int i = 1; i < distances.length; i++) {
            if(max < distances[i]){
                max = distances[i];
                node = i;
            }
        }
        return node;
    }
    static int findRoot(int[] parents){
        for (int i = 1; i < parents.length; i++) {
            if(parents[i] == 0){
                return i;
            }
        }
        return 0;
    }
    static class Edge{
        int child;
        int distance;

        public Edge() {
        }
        public Edge(int child, int distance) {
            this.child = child;
            this.distance = distance;
        }
    }
}
