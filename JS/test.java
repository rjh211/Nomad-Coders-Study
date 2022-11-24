import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class test{
    public static void main(String[] args) {
        // System.out.println(Solution.solution(8, new int[]{1, 3, 7}));
        for(int s : Solution.solution(2, new int[]{1})){
            System.out.println(s + " ");
        }
        // for(int i : IntStream.rangeClosed(1, 10).toArray()){
        //     System.out.println(i + " : " + Solution.solution(i));
        // }
    }
    
    class Solution {
        public static int[] solution(int e, int[] starts) {
            int[] answer = new int[starts.length];
            int[] basicArray = new int[e - starts[0] + 1];
            Arrays.sort(starts);
            for(int j = 0; j < starts.length; j++){
                int[] maxValue = new int[]{-1, 0};
                for(int i = starts[j] - starts[0]; i < basicArray.length; i++){
                    int methodValue = method(i + starts[0]);
                    basicArray[i] = methodValue;
                    if(methodValue > maxValue[0]){
                        maxValue[0] = methodValue;
                        maxValue[1] = i + starts[0];
                    }
                }
                answer[j] = maxValue[1];
            }
            
            return answer;
        }

        public static int method(int number){
            int count = 0;
            for (int i = 1; i * i <= number; i++) {
                if (i * i == number) count++;
                else if (number % i == 0) count += 2;
            }
            return count;
        }
    }
}
