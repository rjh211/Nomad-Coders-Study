import java.util.Arrays;

class test{
    public static void main(String[] args) {
        System.out.println(Solution.solution(new int[][]{{0, 1}, {3, 9}, {2, 5}}));
    }
    
    class Solution {
        static int result = 0;
        public static int solution(int[][] lines) {
            int[] line = new int[201];

            for(int i = 0 ; i < lines.length; i++){
                for(int j = lines[i][0]; j <= lines[i][1] ; j++){
                    line[j + 100]++;
                }
            }
            return (int) Arrays.stream(line).filter(e-> e > 1).count();
        }
    }
}
