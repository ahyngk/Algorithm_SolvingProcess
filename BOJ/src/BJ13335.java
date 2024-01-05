import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ13335 {
    static int trucks;
    static int bridgeLength;
    static int bridgeMaxWeight;
    static int[] truckWeights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        trucks = Integer.parseInt(st.nextToken());
        bridgeLength = Integer.parseInt(st.nextToken());
        bridgeMaxWeight = Integer.parseInt(st.nextToken());
        truckWeights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(run());
    }
    static int run(){
        int index = 0;
        int time = 1;
        List<Truck> onBridge = new ArrayList<>();
        addTruck(onBridge,index);

        while (true){
            time++;
            int currentWeight = moveTruck(onBridge);
            if(index<truckWeights.length-1 && currentWeight+truckWeights[index+1] <= bridgeMaxWeight){
                index++;
                addTruck(onBridge,index);
            }
            if(index == truckWeights.length-1 && onBridge.isEmpty()){
                break;
            }
        }
        return time;
    }
    static void addTruck(List<Truck> onBridge, int index){
        Truck truck = new Truck();
        truck.disLeft = bridgeLength;
        truck.weight = truckWeights[index];
        onBridge.add(truck);
    }
    static int moveTruck(List<Truck> onBridge){
        int currentWeight = 0;
        for (int i = 0; i < onBridge.size(); i++) {
            onBridge.get(i).disLeft--;
            if(hasTruckLeft(onBridge, i)){
                i--;
                continue;
            }
            currentWeight += onBridge.get(i).weight;
        }
        return currentWeight;
    }
    static boolean hasTruckLeft(List<Truck> onBridge, int index){
        if(onBridge.get(index).disLeft == 0){
            onBridge.remove(index);
            return true;
        }
        return false;
    }
    static class Truck {
        int disLeft;
        int weight;
    }
}
