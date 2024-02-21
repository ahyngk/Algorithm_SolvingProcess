import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ14725 {
    static Node entrance;
    static Node last;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        entrance = new Node();
        entrance.child = new ArrayList<>();
        entrance.info = "THISISTHEFIRSTNODE";
        last = entrance;
        read();
        Collections.sort(entrance.child);
        for (int i = 0; i < entrance.child.size(); i++) {
            mapDepth(0,entrance.child.get(i));
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
//        System.out.println("currentLine = " + Arrays.toString(currentLine));
        Node currentFirst = findHead(entrance, currentLine[0]);
        if(currentFirst == null){
            currentFirst = new Node();
            currentFirst.info = currentLine[0];
            currentFirst.child = new ArrayList<>();
            currentFirst.parent = entrance;
            entrance.child.add(currentFirst);
        }
//        System.out.println("currentFirst = " + currentFirst.info);
        Found found = findLast(0, currentFirst, currentLine);
        Node currentLast = found.node;
        if(!found.node.parent.equals(entrance)){
            currentLast = found.node.parent;
        }
        int index = found.index;
//        System.out.println("currentLast = " + currentLast.info);
        if(index == 0){
            index = 1;
        }
        for (int i = index; i < currentLine.length; i++) {
            Node nextNode = new Node();
            nextNode.info = currentLine[i];
            nextNode.child = new ArrayList<>();
            nextNode.parent = currentLast;
            currentLast.child.add(nextNode);
            currentLast = nextNode;
        }
    }
    static Node findHead(Node current, String info){
        if(!current.child.isEmpty() && current.info.equals(info)){
            return current;
        }
        Node found = null;
        for (int i = 0; i < current.child.size(); i++) {
            Node find = findHead(current.child.get(i), info);
            if(find != null){
                found = find;
            }
        }
        return found;
    }
    static Found findLast(int index, Node currentNode, String[] arr){
        Found found = new Found(currentNode, index);
        if(index<arr.length && currentNode.info.equals(arr[index])){
            for (int i = 0; i < currentNode.child.size(); i++) {
               Found temp = findLast(index+1, currentNode.child.get(i), arr);
               if(temp.index > found.index){
                   found = temp;
               }
            }
        }
        return found;
    }
    static void mapDepth(int depth, Node currentNode){
        makeString(depth, currentNode);
        Collections.sort(currentNode.child);
        for (int i = 0; i < currentNode.child.size(); i++) {
            mapDepth(depth+1, currentNode.child.get(i));
        }
    }
    static void makeString(int depth, Node node){
        for (int i = 0; i < depth*2; i++) {
            sb.append("-");
        }
        sb.append(node.info).append("\n");
    }
    static class Found {
        Node node;
        int index;
        public Found(){}
        public Found(Node node, int index) {
            this.node = node;
            this.index = index;
        }
    }
    static class Node implements Comparable<Node>{
        Node parent;
        List<Node> child;
        String info;

        @Override
        public int compareTo(Node node){
            return this.info.compareTo(node.info);
        }
    }
}
