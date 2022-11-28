import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class test{
    public static void main(String[] args) {
        Solution.solution(12345);
        // System.out.println(Solution.solution(1));
        // for (int i = 1; i < 500; i++) {
        //     for (int j = 1; j <= i; j++) {
        //         Solution.solution(i, IntStream.rangeClosed(1, j).toArray());
        //     }
        // }
        // System.out.println(Solution.solution(8, new int[]{1, 3, 7}));
        // for(int s : Solution.solution(8, new int[]{1,3,7,8})){
        //     System.out.println(s + " ");
        // }
        // for(int i : IntStream.rangeClosed(1, 10).toArray()){
        //     System.out.println(i + " : " + Solution.solution(i));
        // }
    }
    
    class Solution {
        public static void solution(int N) {
            int enable_print = N % 10;
            while(N > 0) {
                if(true){
                    enable_print = 1;
                }
                if(enable_print == 1){
                    System.out.print(N % 10);
                }
                N = N / 10;
            }
        }
    }
}
