import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class test{
    public static void main(String[] args) {
        System.out.println(Solution.solution4(new int[] {1,2,3}));
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
        public static int[] solution(int e, int[] starts) {
            int[] answer = new int[starts.length];
            int[] stdArr = Arrays.stream(IntStream.rangeClosed(starts[0], e).toArray()).map(i->method(i)).toArray();
            for (int i = 0; i < answer.length; i++) {
                int max = -1;
                for (int j : Arrays.copyOfRange(stdArr, starts[i] - starts[0], stdArr.length)) {
                    if(max < j){
                        max = j;
                    }
                }
                for (int j = starts[i] - starts[0]; j < stdArr.length; j++) {
                    if(max == stdArr[j]){
                        answer[i] = j + starts[0];
                        break;
                    }
                }
            }
            return answer;
        }

        public static int[] solution2(int e, int[] starts){
            int[] answer = new int[starts.length];
            for(int j = 0; j < starts.length; j++){
                int[] maxValue = new int[]{-1, 0};
                for(int i = starts[j]; i <= e; i++){
                    int methodResult = method(i);
                    if(maxValue[0] < methodResult){
                        maxValue[0] = methodResult;
                        maxValue[1] = i;
                    }
                }
                if(j > 0 && answer[j - 1] >= starts[j] && answer[j - 1] != maxValue[1]){
                    System.out.println(1);
                }
                answer[j] = maxValue[1];
            }
            return answer;
        }

        public static int[] solution3(int e, int[] starts){
            int[] answer = new int[starts.length];
            for(int j = 0; j < starts.length; j++){
                if(j != 0 && answer[j - 1] > starts[j]){
                    answer[j] = answer[j-1];
                    continue;
                }
                int[] maxValue = new int[]{-1, 0};
                for(int i = starts[j]; i <= e; i++){
                    int methodResult = method(i);
                    if(maxValue[0] < methodResult){
                        maxValue[0] = methodResult;
                        maxValue[1] = i;
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

        public static int solution4(int[] A){
            int[] result = Arrays.stream(A).filter(e->e>0).distinct().sorted().toArray();
            if(result.length == 0 || result[0] != 1) return 1;
            for (int i = 1; i < result.length; i++) {
                if(i != result[i] - 1){
                    return i + 1;
                }
            }
            return result.length + 1;
        }
    }
}
