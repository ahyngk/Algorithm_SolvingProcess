import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이 방법
 * 4방향으로 직선으로 이동하며 도착지에 가는지 탐색
 *
 * 1. BFS 시도 (실패)
 * 탐색을 하다가 방향이 바뀌는 경우에 거울 개수를 +1
 * question : 모든 경우의 수를 다 가보지 않고 최소를 찾을 수 있나?
 * question : 모든 경로를 다 확인한다고 했을 때의 시간복잡도는?
 * result : 방문 체크를 위해 큐에 방문 배열을 모두 넣는 방식으로 하니 메모리 초과
 *
 * 2. DFS 시도 (실패)
 * 어차피 모든 경로를 시도해야 한다면, 메모리 초과는 발생하지 않을 것이라는 생각
 * result : 메모리 초과
 *
 * 3. 다익스트라 (힌트 봄)
 * 방문 체크에서 지금까지 사용한 거울 수가 더 많은 경우는 더 이상 탐색할 필요가 없을 것
 * issue : 거울 사용 횟수만으로 방문 처리를 하니까, 다른 방향에서 같은 개수로 도착한 경우를 파악할 수 없었음
 * solve : 도착하는 방향에 따라 방문 다익스트라 배열을 3차원으로 만들어 처리
 * **/


public class BJ6087 {
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    static String[][] map;
    static int[][] laser = new int[2][2];
    static int smallestMirror = Integer.MAX_VALUE;
    static int[][][] visited;

    public static void main(String[] args) {
        try {
            read();
            findLaser();
            findRoute();
//            printVisited();
            System.out.println(smallestMirror);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int columns = Integer.parseInt(st.nextToken());
        int rows = Integer.parseInt(st.nextToken());
        map = new String[rows][columns];
        for (int i = 0; i < map.length; i++) {
            map[i] = br.readLine().split("");
        }
    }
    static void findLaser(){
        int index = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j].equals("C")){
                    laser[index] = new int[] {i,j};
                    index++;
                    if(index == laser.length){
                        return;
                    }
                }
            }
        }
    }
    static void printVisited(){
        for (int i = 0; i < visited.length; i++) {
            System.out.println(Arrays.toString(visited[i]));
        }
    }
    static void findRoute(){
        visited = new int[map.length][map[0].length][4];
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        visited[laser[0][0]][laser[0][1]][0] = 0;
        visited[laser[0][0]][laser[0][1]][1] = 0;
        visited[laser[0][0]][laser[0][1]][2] = 0;
        visited[laser[0][0]][laser[0][1]][3] = 0;

        Route firstRoute = new Route(laser[0][0], laser[0][1], 0, -1);
        Queue<Route> queue = new LinkedList<>();
        queue.add(firstRoute);

        while (!queue.isEmpty()){
            Route currentRoute = queue.poll();
            if(!(firstRoute.x == currentRoute.x && firstRoute.y == currentRoute.y) && map[currentRoute.x][currentRoute.y].equals("C")){
                smallestMirror = Math.min(smallestMirror, currentRoute.mirror);
                continue;
            }
            for (int i = 0; i < directions.length; i++) {
                int nextX = currentRoute.x + directions[i][0];
                int nextY = currentRoute.y + directions[i][1];
                if(checkBorder(nextX, nextY) && !map[nextX][nextY].equals("*")){
                    Route nextRoute = new Route(nextX, nextY, currentRoute.mirror,i);
                    if(!(currentRoute.beforeDir == -1 || currentRoute.beforeDir == nextRoute.beforeDir)){
                        nextRoute.mirror += 1;
                    }
                    if(visited[nextX][nextY][i] > nextRoute.mirror){
                        visited[nextX][nextY][i] = nextRoute.mirror;
                        queue.add(nextRoute);
                    }
                }
            }
        }
    }
    static boolean checkBorder(int x, int y){
        if(x>=0 && x< map.length && y>=0 && y<map[0].length){
            return true;
        }
        return false;
    }
    static class Route{
        int x;
        int y;
        int mirror;
        int beforeDir;
        Route(){}
        Route(int x, int y, int mirror, int beforeDir){
            this.x = x;
            this.y = y;
            this.mirror = mirror;
            this.beforeDir = beforeDir;
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("x: "+x+" y: "+y+" mirror: "+mirror+"\n");
            return sb.toString();
        }
    }
}
