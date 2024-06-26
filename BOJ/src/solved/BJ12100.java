package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ12100 {
    // Directions : [startPoint, endPoint]
    // x up, x down, y left, y right
    static int[][] directions;
    static long biggest = -1;
    static int totalMove = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int boardSize = Integer.parseInt(br.readLine());
        // Board Input
        long[][] board = new long[boardSize][boardSize];
        for (int i = 0; i < board.length; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        }
        // Define Directions
        directions = new int[4][3];
        directions[0] = new int[] {0,board.length-1,1};
        directions[1] = new int[] {board.length-1,0,-1};
        directions[2] = new int[] {0,board.length-1,1};
        directions[3] = new int[] {board.length-1,0,-1};

        // call Move Choosing Function
        int[] eachMove = new int[5];
        chooseMoves(0,eachMove,board);
        // Answer
        System.out.println(biggest);
    }
    // Function.1 Choosing all five moves
    static void chooseMoves(int currentMove, int[] eachMove, long[][] board){
        // End Point : Chosen all five moves
        if(currentMove==5){
            // Create new board
            long[][] boardTemp = new long[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                boardTemp[i] = board[i].clone();
            }
            // Call Movement function, store result
            biggest = Math.max(movements(eachMove,boardTemp),biggest);
            // call calculation function
            return;
        }

        // Choose movement in Each Direction
        for (int i = -1; i < directions.length; i++) {
            eachMove[currentMove] = i;
            chooseMoves(currentMove+1,eachMove,board);
        }
    }
    // Function 2. Call each Moves, total 5 (according to result of move choosing function)
    static long movements(int[] eachMove, long[][] board){
        long currentBiggest = -1;
        for (int i = 0; i < eachMove.length; i++) {
            if(eachMove[i] == 0 || eachMove[i] == 1){
                currentBiggest = Math.max(verticalMove(eachMove[i],board),currentBiggest);
                continue;
            }
            if(eachMove[i] == 2 || eachMove[i] == 3){
                currentBiggest = Math.max(horizontalMovement(eachMove[i],board),currentBiggest);
                continue;
            }
        }
        return currentBiggest;
    }
    // Function 3. Vertical Move : decides where to move by direction
    static long verticalMove(int direction, long[][] board){
        long currentBig = 0;

        for (int j = 0; j < board[0].length; j++) {
            List<Long> temp = new ArrayList<>();
            Queue<Long> eachLine = new LinkedList<>();
            int start = directions[direction][0];
            while (start>=0 && start<board.length){
                if(board[start][j]!=0) {
                    temp.add(board[start][j]);
                }
                start+=directions[direction][2];
            }
            for (int i = 0; i < temp.size(); i++) {
                if(i!=temp.size()-1 && temp.get(i)-temp.get(i+1)==0){
                    eachLine.add(temp.get(i)+temp.get(i+1));
                    i+=1;
                }
                else {
                    eachLine.add(temp.get(i));
                }
            }

            start = directions[direction][0];
            while (start>=0 && start<board.length){
                if(!eachLine.isEmpty()){
                    board[start][j] = eachLine.poll();
                    currentBig = Math.max(currentBig,board[start][j]);
                }
                else {
                    board[start][j] = 0;
                }
                start+=directions[direction][2];
            }
        }
        return currentBig;
    }
    // Function 4. Horizontal Move
    static long horizontalMovement(int direction, long[][] board){
        long currentBig = 0;

        for (int i = 0; i < board.length; i++) {
            List<Long> temp = new ArrayList<>();
            Queue<Long> eachLine = new LinkedList<>();
            int start = directions[direction][0];
            while (start>=0 && start<board.length){
                if(board[i][start]!=0) {
                    temp.add(board[i][start]);
                }
                start+=directions[direction][2];
            }
            for (int j = 0; j < temp.size(); j++) {
                if(j!=temp.size()-1 && temp.get(j)-temp.get(j+1)==0){
                    eachLine.add(temp.get(j)+temp.get(j+1));
                    j+=1;
                }
                else {
                    eachLine.add(temp.get(j));
                }
            }

            start = directions[direction][0];
            while (start>=0 && start<board.length){
                if(!eachLine.isEmpty()){
                    board[i][start] = eachLine.poll();
                    currentBig = Math.max(currentBig,board[i][start]);
                }
                else {
                    board[i][start] = 0;
                }
                start+=directions[direction][2];
            }
        }
        return currentBig;
    }
}
