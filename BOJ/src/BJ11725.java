import java.io.*;
import java.util.*;
public class BJ11725 {
    static Node[] tree;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());
        tree = new Node[size+1];
        parents = new int[tree.length];
        Arrays.fill(parents,0);
        for (int i = 1; i < tree.length; i++) {
            tree[i] = new Node();
            tree[i].num = i;
            tree[i].connected = new ArrayList<>();
        }

        for (int i = 0; i < size-1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            tree[node1].connected.add(tree[node2]);
            tree[node2].connected.add(tree[node1]);
        }
        findParent(1);
        for (int i = 2; i < parents.length; i++) {
            System.out.println(parents[i]);
        }
    }
    static void findParent(int current){
        for (int i = 0; i < tree[current].connected.size(); i++) {
            if(parents[tree[current].connected.get(i).num] == 0){
                parents[tree[current].connected.get(i).num] = current;
                findParent(tree[current].connected.get(i).num);
            }
        }
    }
    static class Node{
        int num;
        List<Node> connected;
        public Node() {
        }
    }
}
