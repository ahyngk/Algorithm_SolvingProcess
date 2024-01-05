import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1238 {
    // 인접 리스트 방식으로 그래프 입력
    static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodes = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        int place = Integer.parseInt(st.nextToken());

        // 배열 안에 리스트가 있는 구조이므로 NullPointerException 방지를 위해 미리 리스트 생성
        graph = new List[nodes+1];
        for (int i = 0; i <graph.length ; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            graph[start].add(new int[] {end,distance});
        }

        // 전체 노드를 순회하며 만나는 지점이 아닌 경우
        // start -> end 최단거리, end -> start 최단거리를 각각 구해 더함
        // 최대값 갱신
        int max = 0;
        for (int i = 1; i < graph.length; i++) {
            if(i!=place){
                int travelDis = run(i,place) + run(place,i);
                max = Math.max(travelDis,max);
            }
        }
        System.out.println(max);
    }
    // 다익스트라 알고리즘 구현
    static int run (int start, int end){
        // 최단거리를 갱신하는 방문 배열
        int[] distance = new int[graph.length];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[start] = 0;
        // 효율적인 탐색을 위해 우선순위 큐 사용
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
            // 디악스트라 일고리즘(우선순위 큐)을 사용할 경우 종료 지점에 처음 도달했을 때 최단 거리임이 보장됨
            // 여기서 종료하지 않으면 메모리 초과
            if(current[0] == end){
                break;
            }
            for (int i = 0; i < graph[current[0]].size(); i++) {
                int[] next = {graph[current[0]].get(i)[0], current[1]+graph[current[0]].get(i)[1]};
                if(distance[next[0]] > next[1]){
                    distance[next[0]] = next[1];
                    queue.add(next);
                }
            }
        }
        // start -> end로의 최단거리 반환
        return distance[end];
    }
}
