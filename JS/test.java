import java.util.Arrays;
import java.util.stream.IntStream;

class test{
    public static void main(String[] args) {
        for(String s : Solution.solution(new String[]{"3 - 4 = -3","5 + 6 = 11"})){
            System.out.println(s + " ");
        }
    }
    
    class Solution {
        public static String[] solution(String[] quiz) {
            String[] answer = new String[quiz.length];
            for(int i = 0; i< quiz.length; i++){
                String[] parseStr = quiz[i].split(" = ");
                int result = Integer.parseInt(parseStr[1]);
                String[] strArr = null;
                int value = 0;
                if(parseStr[0].contains("+")){
                    parseStr[0] = parseStr[0].replace(" + ", " - ");
                    strArr = parseStr[0].split(" - ");
                    value += Integer.parseInt(strArr[0]) + Integer.parseInt(strArr[1]);
                } else {
                    strArr = parseStr[0].split(" - ");
                    value += Integer.parseInt(strArr[0]) - Integer.parseInt(strArr[1]);
                }

                answer[i] = value == result ? "O" : "X";
            }
            return answer;
        }
    }
}
