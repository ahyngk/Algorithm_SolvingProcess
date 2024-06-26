package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ12919_01 {
    static String start;
    static String des;
    static boolean check = false;
    public static void main(String[] args) throws IOException{
        read();
        select(0,0,des.length(), false);
        System.out.println(check == true ? 1 : 0);
    }
     static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        start = br.readLine();
        des = br.readLine();
    }
    // 초기 접근은 선택을 완료한 이후 가능한 선택지인지 체크하는 방식으로 진행 -> 2^50가지를 모두 확인하는 것은 동일하므로 시간초과
    // 재귀 선택 과정에서 선택이 불가능한 선택지의 경우 진행하지 않는 방식으로 변경 필요
    static void select(int index, int startIndex, int lastIndex, boolean isReversed){
        if(check){
            return;
        }
        if(index == des.length() - start.length()){
            checkSame(des.substring(startIndex,lastIndex), isReversed);
            return;
        }

        if(checkA(startIndex,lastIndex,isReversed)){
            int nextstartIndex = isReversed ? startIndex+1 : startIndex;
            int nextlastIndex = isReversed ? lastIndex : lastIndex-1;
            select(index+1, nextstartIndex, nextlastIndex, isReversed);
        }
        if (checkB(startIndex, lastIndex,isReversed)) {
            int nextstartIndex = isReversed ? startIndex : startIndex+1;
            int nextlastIndex = isReversed ? lastIndex-1 : lastIndex;
            select(index+1, nextstartIndex, nextlastIndex, !isReversed);
        }
    }
    static boolean checkA(int startIndex, int lastIndex, boolean isReversed){
        if(!isReversed && des.charAt(lastIndex-1) == 'A'){
            return true;
        }
        else if (isReversed && des.charAt(startIndex) == 'A'){
            return true;
        }
        return false;
    }
    static boolean checkB(int startIndex, int lastIndex, boolean isReversed){
        if(!isReversed  && des.charAt(startIndex) == 'B'){
            return true;
        }
        else if (isReversed  && des.charAt(lastIndex-1) == 'B'){
            return true;
        }
        return false;
    }
    static void checkSame(String s, boolean isReversed){
        if(isReversed){
            s = new StringBuilder(s).reverse().toString();
        }
        if(s.equals(start)){
            check = true;
        }
    }
}
