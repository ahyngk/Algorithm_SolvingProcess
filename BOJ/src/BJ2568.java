import java.io.*;
import java.util.*;

public class BJ2568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int num = Integer.parseInt(br.readLine());

        EachLine[] lineArr = new EachLine[num];
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < lineArr.length; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lineArr[i] = new EachLine(start,end);
            indexes.add(i);
        }
        Arrays.sort(lineArr);

        List<Lines> list = new ArrayList<>();
        for (int i = 0; i < lineArr.length; i++) {
            boolean check = false;
            for (int j = 0; j < list.size(); j++) {
                if(list.get(j).start>lineArr[i].a && list.get(j).last>lineArr[i].b){
                    check = true;
                    list.get(j).start = lineArr[i].a;
                    list.get(j).selected.add(j);
                }
                if(list.get(j).start<lineArr[i].a && list.get(j).last<lineArr[i].b){
                    check = true;
                    list.get(j).last = lineArr[i].b;
                    list.get(j).selected.add(i);
                }
            }
            if(!check){
                Lines temp = new Lines();
                temp.start = lineArr[i].a;
                temp.last = lineArr[i].b;
                temp.selected = new ArrayList<>();
                temp.selected.add(i);
                list.add(temp);
            }
        }

        Collections.sort(list);
        System.out.println(lineArr.length - list.get(0).selected.size());
    }
    static class EachLine implements Comparable<EachLine> {
        int a;
        int b;

        public EachLine(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(EachLine o) {
            return this.a-o.a;
        }
    }
    static class Lines implements Comparable<Lines>{
        int start;
        int last;
        List<Integer> selected;

        @Override
        public String toString() {
            return "Lines{" +
                    "start=" + start +
                    ", last=" + last +
                    ", selected=" + selected +
                    '}';
        }

        @Override
        public int compareTo(Lines o) {
            return o.selected.size() - this.selected.size();
        }
    }
}
