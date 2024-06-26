package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BJ1027 {
    static long[] buildingHeights;
    static List<Integer>[] buildingSights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int buildings = Integer.parseInt(br.readLine());
        buildingHeights = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        buildingSights = new List[buildingHeights.length];
        for (int i = 0; i < buildingSights.length; i++) {
            buildingSights[i] = new ArrayList<>();
        }

        for (int i = 0; i <buildingHeights.length ; i++) {
            if(i!=buildingHeights.length-1){
                buildingSights[i+1].add(i);
                buildingSights[i].add(i+1);
            }
            for (int j = i+2; j < buildingHeights.length; j++) {
                if(decide(i,j)){
                    buildingSights[i].add(j);
                    buildingSights[j].add(i);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < buildingSights.length; i++) {
            max = Math.max(max, buildingSights[i].size());
        }
        System.out.println(max);
    }
    static boolean decide(int startB, int endB){
        long height = Math.abs(buildingHeights[startB] - buildingHeights[endB]);
        int width = endB - startB;
        double ratio = (double)height/(double)width;
        boolean check = true;

        if(buildingHeights[startB] > buildingHeights[endB]){
            for (int i = startB+1; i <endB; i++) {
                double currentSpot = buildingHeights[startB] - ratio*(i - startB);
                if(buildingHeights[i] >= currentSpot){
                    check = false;
                    break;
                }
            }
        }
        else {
            for (int i = startB+1; i <endB; i++) {
                double currentSpot = buildingHeights[startB] + ratio*(i - startB);
                if(buildingHeights[i] >= currentSpot){
                    check = false;
                    break;
                }
            }
        }
        return check;
    }
}
