package solved;

import java.util.*;

/*
* 프로그래머스 에어컨
* https://school.programmers.co.kr/learn/courses/30/lessons/214289
*
* 접근 방법
* 시간*온도 2차원 배열을 만들어 둔 뒤 시간 순으로 보며 해당 시간에 해당 온도에 도달하는 최소 비용을 저장해두는 방식
* DP, 메모이제이션, 구현
*
* 후기
* DP로 접근하는 개념 자체는 맞음
* 가능한 경우의 점화식을 좀 더 빠르게 세우는 연습이 필요할 것 같음
* 경우 판단과 문제 이해 부분에서 시간이 많이 걸렸다
*
* */


public class 에어컨 {
    int[] times;
    int[][] cases;

    int minTemp;
    int maxTemp;
    int outSide;

    int difPrice;
    int samePrice;

    int[] types = {-1, 0, 1};

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        // 온도 범위는 -100 ~ 100까지로 설정
        times = onboard;

        minTemp = t1+100;
        maxTemp = t2+100;
        outSide = temperature+100;

        difPrice = a;
        samePrice = b;

        cases = new int[onboard.length][201];
        for(int[] temp: cases){
            Arrays.fill(temp, Integer.MAX_VALUE);
        }
        cases[0][outSide] = 0;

        // 로직 실행
        run();
        return getAnswer();
    }

    private void run(){
        for(int time=0; time<times.length-1; time++){
            for(int temp=0; temp<cases[time].length; temp++){
                // -1, 0, +1 도 중 가능한 온도 확인 후 비용 산정
                if(cases[time][temp] != Integer.MAX_VALUE){
                    for(int i=0; i<types.length; i++){
                        int target = temp + types[i];
                        if((target >=0 && target < cases[time].length) && (times[time+1] != 1 || (target >= minTemp && target <= maxTemp))){
                            int onPrice = on(temp, types[i]);
                            int offPrice = off(temp, types[i]);
                            int price = Math.min(onPrice, offPrice);
                            cases[time+1][target] = Math.min(cases[time][temp]+price, cases[time+1][target]);
                        }
                    }
                }
            }
        }
    }

    // 에어컨을 가동한다 (실현 가능한 온도인지 판별 후 비용 반환)
    private int on(int temp, int type){
        if(type == 0){
            return samePrice;
        }
        return difPrice;
    }

    // 에어컨을 가동하지 않는다
    private int off(int temp, int type){
        // -1도 : 실외 온도가 현재 온도보다 낮다
        if(type == -1 && outSide < temp){
            return 0;
        }
        // 0도 : 실외 온도가 현재 온도와 같다
        if(type == 0 && outSide == temp ){
            return 0;
        }
        // +1도 : 실외 온도가 현재 온도보다 높다
        if(type == 1 && outSide > temp){
            return 0;
        }
        return Integer.MAX_VALUE;
    }

    private int getAnswer(){
        int answer = Integer.MAX_VALUE;
        for(int temp=0; temp<cases[cases.length-1].length; temp++){
            answer = Math.min(answer, cases[cases.length-1][temp]);
        }
        return answer;
    }
}