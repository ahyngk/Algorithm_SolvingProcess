import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ14725 {
    static Node entrance;
    static StringBuilder sb = new StringBuilder();
    static Map<String,List<Node>> nodeMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        entrance = new Node();
        entrance.child = new ArrayList<>();
        entrance.info = "FIRSTNODE";
        read();
        Collections.sort(entrance.child);
        for (int i = 0; i < entrance.child.size(); i++) {
            mapDepth(entrance.child.get(i));
        }
        System.out.print(sb);
    }
    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int rows = Integer.parseInt(br.readLine());
        for (int i = 0; i < rows; i++) {
            String[] currentLine = br.readLine().split(" ");
            makeNodeList(Arrays.copyOfRange(currentLine, 1, currentLine.length));
        }
    }
    static void makeNodeList(String[] currentLine){
        List<Node> firstCandidate = findFirst(currentLine[0]);
        Node last = new Node();
        last.depth = -1;
        if(firstCandidate.isEmpty()){
            Node first = new Node();
            first.info = currentLine[0];
            first.child = new ArrayList<>();
            first.parent = entrance;
            first.depth = 0;
            entrance.child.add(first);
            nodeMap.put(first.info, new ArrayList<>());
            nodeMap.get(first.info).add(first);
            last = first;
        }
        else {
            for (int i = 0; i < firstCandidate.size(); i++) {
                Node temp = findLast(0,firstCandidate.get(i), currentLine);
                if(temp.depth > last.depth){
                    last = temp;
                }
            }
        }
        if(last.depth > 0){
            last = last.parent;
        }
        for (int i = last.depth+1; i < currentLine.length; i++) {
            Node nextNode = new Node();
            nextNode.info = currentLine[i];
            nextNode.child = new ArrayList<>();
            nextNode.depth = i;
            nextNode.parent = last;
            last.child.add(nextNode);
            last = nextNode;
            if(!nodeMap.containsKey(nextNode.info)){
                nodeMap.put(nextNode.info, new ArrayList<>());
            }
            nodeMap.get(nextNode.info).add(nextNode);
        }
    }
    static List<Node> findFirst(String string){
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < entrance.child.size(); i++) {
            if(entrance.child.get(i).info.equals(string)){
                nodes.add(entrance.child.get(i));
            }
        }
        return nodes;
    }
    static Node findLast(int index, Node current, String[] currentLine){
        Node result = current;
        if(current.info.equals(currentLine[index])){
            for (int i = 0; i < current.child.size(); i++) {
                Node temp = findLast(index+1, current.child.get(i), currentLine);
                if(temp.depth > result.depth){
                    result = temp;
                }
            }
        }
        return result;
    }
    static boolean search(Node node, String[] currentLine, int index){
        if(index < 0){
            return true;
        }
        else if(node.info.equals(currentLine[index]) && node.parent != null){
            return search(node.parent, currentLine, index-1);
        }
        else {
            return false;
        }
    }
    static void mapDepth(Node currentNode){
        makeString(currentNode);
        Collections.sort(currentNode.child);
        for (int i = 0; i < currentNode.child.size(); i++) {
            mapDepth(currentNode.child.get(i));
        }
    }
    static void makeString(Node node){
        for (int i = 0; i < node.depth*2; i++) {
            sb.append("-");
        }
        sb.append(node.info).append("\n");
    }
    static class Node implements Comparable<Node>{
        Node parent;
        List<Node> child;
        String info;
        int depth;

        @Override
        public int compareTo(Node node){
            return this.info.compareTo(node.info);
        }

        @Override
        public String toString() {
            return "Node{" + "info='" + info + '\'' +
                    ", depth=" + depth +
                    '}';
        }
    }
}
