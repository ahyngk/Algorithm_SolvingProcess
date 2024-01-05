import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ10815 {
    static long[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long size = Long.parseLong(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(arr);

        long findSize = Long.parseLong(br.readLine());
        long[] toFind = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        for (int i = 0; i < toFind.length; i++) {
            binarySearch(toFind[i],0,arr.length-1);
        }
        System.out.println(sb);
    }
    static void binarySearch(long find, int start, int end){
        int mid = (start+end)/2;
        if(arr[mid] == find){
            sb.append("1").append(" ");
            return;
        }
        if(start>=end){
            sb.append("0").append(" ");
            return;
        }
        if(arr[mid] > find){
            binarySearch(find,start,mid);
        }
        else if (arr[mid] < find) {
            binarySearch(find,mid+1,end);
        }

    }
}
