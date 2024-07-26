package solved;

import java.util.*;
import java.io.*;

public class BOJ2310 {
    static int[] prices;
    static List<Integer>[] graph;
    static int[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            int roomCnt = Integer.parseInt(br.readLine());
            if(roomCnt == 0){
                break;
            }
            init(br, roomCnt);
            sb.append(run()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void init(BufferedReader br, int roomCnt) throws IOException{
        prices = new int[roomCnt];
        graph = new List[roomCnt];
        visited = new int[roomCnt];
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList();
        }
        Arrays.fill(visited, -1);

        StringTokenizer st;
        for(int i=0; i<roomCnt; i++){
            st = new StringTokenizer(br.readLine());
            String kind = st.nextToken();
            prices[i] = kind.equals("T")? Integer.parseInt(st.nextToken())*-1 : Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()) {
                int room = Integer.parseInt(st.nextToken()) -1;
                if(room == -1){
                    break;
                }
                graph[i].add(room);
            }
        }
    }

    private static String run(){
        String answer = "No";
        int deposit = getPrice(0,0);
        if(deposit < 0){
            return answer;
        }

        PriorityQueue<Player> que = new PriorityQueue();
        que.add(new Player(0, deposit));
        visited[0] = deposit;

        while(!que.isEmpty()){
            Player current = que.poll();
            if(current.position == graph.length -1){
                answer = "Yes";
                break;
            }

            for(int i=0; i<graph[current.position].size(); i++){
                int nextPosition = graph[current.position].get(i);
                int nextDeposit = getPrice(nextPosition, current.deposit);
                if(nextDeposit > visited[nextPosition]){
                    que.add(new Player(nextPosition, nextDeposit));
                    visited[nextPosition] = nextDeposit;
                }
            }
        }
        return answer;
    }

    private static int getPrice(int position, int deposit){
        if(prices[position] <= 0){
            return deposit + prices[position];
        }
        if(prices[position] > deposit){
            return prices[position];
        }
        return deposit;
    }

    static class Player implements Comparable<Player>{
        int position;
        int deposit;

        Player(){}
        Player(int position, int deposit){
            this.position = position;
            this.deposit = deposit;
        }

        @Override
        public int compareTo(Player player){
            return player.deposit - this.deposit;
        }

        @Override
        public String toString(){
            return "Position: "+this.position+" Deposit: "+this.deposit;
        }
    }
}
