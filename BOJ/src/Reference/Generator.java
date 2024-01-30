package Reference;

import java.util.Scanner;

public class Generator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();

        int[][] map = new int[rows][columns];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = (int)(Math.random() * 201) - 100;
            }
        }
        System.out.println(rows+" "+columns);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]);
                if(j != map[0].length-1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
