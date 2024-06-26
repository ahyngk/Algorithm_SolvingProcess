package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String original = br.readLine();
        String explosive = br.readLine();

        // 맨 첫번째 글자만 매핑해두고, 그 지점에서부터만 탐색한다
        // 맨 처음 글자 같을 경우 count 시작
        Stack<Integer> firstIndexes = new Stack<>();
        Stack<EachCharacter> characters = new Stack<>();
        int count = 0;

        label : for (int i = 0; i < original.length(); i++) {
            EachCharacter current = new EachCharacter(original.charAt(i));

            // 맨 처음 글자인 경우
            if(current.c == explosive.charAt(0)){
                count = 1;
                firstIndexes.add(characters.size());
            }
            // 나머지 글자인 경우
            else if (count > 0 && current.c == explosive.charAt(count)) {
                count+=1;
            }
            // 아예 다른 글자인경우 (연속되지 않음)
            else {
                count = 0;
            }
            current.count = count;
            characters.add(current);

            // 마지막 글자인 경우 : 개수만큼 스택 없애기
            if(count == explosive.length()){
                int currentFirst = firstIndexes.pop();
                while (characters.size()>currentFirst){
                    characters.remove(characters.size()-1);
                }
                count = characters.isEmpty()? 0 : characters.peek().count;
            }
        }
        if(characters.isEmpty()){
            System.out.println("FRULA");
        }
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < characters.size(); i++) {
                sb.append(characters.get(i).c);
            }
            System.out.println(sb);
        }
    }
    static class EachCharacter{
        char c;
        int count;

        public EachCharacter(char c) {
            this.c = c;
        }
    }
}
