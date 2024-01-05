import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ7682_unsolved {
    static String[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String s = br.readLine();
            if(s.equals("end")){
                break;
            }
            board = new String[3][3];
            int index = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    board[i][j] = Character.toString(s.charAt(index));
                    index++;
                }
            }
            if(firstCheck() || figureLast()){
                System.out.println("valid");
            }
            else {
                System.out.println("invalid");
            }
        }
    }
    static boolean firstCheck(){
        int totalCount = diagonalCheck(board)+horizontalCheck(board)+verticalCheck(board);
        if(totalCount == 1 || (fullCheck(board) && totalCount == 0)){
            return true;
        }
        return false;
    }
    static boolean figureLast(){
        int totalCount = diagonalCheck(board)+horizontalCheck(board)+verticalCheck(board);
        String[][] newBoard = new String[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            newBoard[i] = board[i].clone();
        }
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[0].length; j++) {
                if(!newBoard[i][j].equals(".")){
                    newBoard[i][j] = ".";
                    if(totalCount == 0 && lastCheck(newBoard)){
                        return true;
                    }
                    newBoard[i][j] = board[i][j];
                }
            }
        }
        return false;
    }
    static boolean lastCheck(String[][] newBoard){
        int totalCount = diagonalCheck(newBoard)+horizontalCheck(newBoard)+verticalCheck(newBoard);
        if(totalCount == 0){
            return true;
        }
        return false;
    }


    static boolean fullCheck(String[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j].equals(".")){
                    return false;
                }
            }
        }
        return true;
    }
    static int verticalCheck(String[][] board){
        int verticalCount = 0;
        for (int j = 0; j < board[0].length; j++) {
            int count = 0;
            String s = board[0][j];
            for (int i = 0; i < board.length; i++) {
                if(board[i][j].equals(s)){
                    count++;
                }
            }
            if(count == 3){
                verticalCount++;
            }
        }
        return verticalCount;
    }
    static int horizontalCheck(String[][] board){
        int verticalCount = 0;
        for (int i = 0; i < board.length; i++) {
            int count = 0;
            String s = board[i][0];
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j].equals(s)){
                    count++;
                }
            }
            if(count == 3){
                verticalCount++;
            }
        }
        return verticalCount;
    }
    static int diagonalCheck(String[][] board){
        int diagonCount = 0;
        if(board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])){
            diagonCount++;
        }
        if(board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])){
            diagonCount++;
        }
        return diagonCount;
    }
}
