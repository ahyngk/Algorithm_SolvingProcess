package retry;

import java.util.*;
import java.io.*;

/*
* 백준 1325 효율적인 해킹
* https://www.acmicpc.net/problem/1325
*
* 접근 방법
* TRY 1. DFS + 메모이제이션 (FAIL)
* 각 정점에서 DFS로 연결된 정점들을 탐색하되, 방문하는 노드들에서 연결된 노드 수를 메모이제이션을 통해 저장하려고 함
* - 그래프가 사이클을 이루는 경우, 메모이제이션 방법으로 할 수 없다
* - 처음 정점에서 시작한 것을 방문 처리를 일관되게 유지하지 않으면 정확한 연결 노드 수 알 수 없다
*
* TRY 2. 모든 정점에서 그냥 DFS (FAIL)
* 시간 초과 발생
* 접근은 맞다. 모든 정점에서 시작하는 것으로 모든 경우를 다 보아야 한다
* 하지만 코드 구현에 따라 시간 복잡도의 차이가 존재하는 것 같다
*
* REFERENCE
* 다른 사람의 풀이를 참고하니 그래프를 인접 리스트로 저장할 때, 주어진 방향 그래도 저장하는 경우를 봄
* 한 노드에서 갈 수 있는 모든 노드의 개수를 찾지 않고, 한 노드로 갈 수 있는 곳(parent)에 신뢰도를 1 증가시키는 방식
* 그래프 방향을 어떻게 저장하는지에 따라 시간 복잡도가 차이가 날까?
* + BFS 구현도 고려
* */


public class BOJ1325_01 {
    static int max = Integer.MIN_VALUE;
    static List<Integer> maxNodes = new ArrayList();
    static Computer[] computers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int computer = Integer.parseInt(st.nextToken());
        int lines = Integer.parseInt(st.nextToken());

        computers = new Computer[computer];
        for (int i = 0; i < computers.length; i++) {
            computers[i] = new Computer(new ArrayList());
        }

        for (int i = 0; i < lines; i++) {
            st = new StringTokenizer(br.readLine());
            int child = Integer.parseInt(st.nextToken()) - 1;
            int parent = Integer.parseInt(st.nextToken()) - 1;
            computers[parent].child.add(child);
        }

        find();
        System.out.println(convertToString());
    }

    private static void find(){
        for (int i = 0; i < computers.length; i++) {
            computers[i].count = recursion(i, new boolean[computers.length]);
            if(max < computers[i].count){
                max = computers[i].count;
                maxNodes.clear();
                maxNodes.add(i+1);
            }
            else if(max == computers[i].count){
                maxNodes.add(i+1);
            }
        }
    }

    private static int recursion(int current, boolean[] visited){
        visited[current] = true;
        int count = 1;
        for (int i = 0; i < computers[current].child.size(); i++) {
            if(!visited[computers[current].child.get(i)]){
                count += recursion(computers[current].child.get(i), visited);
            }
        }
        return count;
    }

    private static String convertToString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxNodes.size(); i++) {
                sb.append(maxNodes.get(i)).append(" ");
        }
        return sb.toString();
    }

    static class Computer{
        int count = -1;
        List<Integer> child;

        public Computer(List child) {
            this.child = child;
        }

        @Override
        public String toString() {
            return "Computer{" +
                    "count=" + count +
                    ", child=" + child +
                    '}';
        }
    }
}
