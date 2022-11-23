import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class test{
    public static void main(String[] args) {
        // System.out.println(Solution.solution(new int[]{1,2,3,4,5,6}, 4));
        for(int s : Solution.solution(new int[]{1,2,3,4,5,6}, 4)){
            System.out.println(s + " ");
        }
        // for(int i : IntStream.rangeClosed(1, 10).toArray()){
        //     System.out.println(i + " : " + Solution.solution(i));
        // }
    }
    
    class Solution {
        public static int[] solution(int[] numlist, int n) {
            Integer[] resultArray = Arrays.stream(numlist).boxed().toArray(Integer[]::new);
            Arrays.sort(resultArray, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    // TODO Auto-generated method stub
                    if(Math.abs(o1 - n) > Math.abs(o2 - n)){
                        return 1;
                    } else if (Math.abs(o1 - n) == Math.abs(o2 - n)){
                        return o1 < o2 ? 1 : -1;
                    } else {
                        return -1;
                    }
                }
                
            });
            return Arrays.stream(resultArray).mapToInt(Integer::intValue).toArray();
        }
    }
}
