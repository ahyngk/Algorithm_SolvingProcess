package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1976 {
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cities = Integer.parseInt(br.readLine());
        int plannedCities = Integer.parseInt(br.readLine());

        graph = new List[cities];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < graph.length; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < temp.length; j++) {
                if(temp[j] == 1){
                    graph[i].add(j);
                }
            }
        }

        String answer = "YES";
        int[] tourPlan = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < tourPlan.length-1; i++) {
            if(!checkPossible(tourPlan[i]-1, tourPlan[i+1]-1)){
                answer = "NO";
                break;
            }
        }
        System.out.println(answer);
    }
    static boolean checkPossible(int start, int end){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visitCheck = new boolean[graph.length];
        queue.add(start);
        visitCheck[start] = true;
        while (!queue.isEmpty()){
            int currentNode = queue.poll();
            if(currentNode == end){
                return true;
            }
            for (int i = 0; i < graph[currentNode].size(); i++) {
                if(!visitCheck[graph[currentNode].get(i)]){
                    visitCheck[graph[currentNode].get(i)] = true;
                    queue.add(graph[currentNode].get(i));
                }
            }
        }
        return false;
    }
}
