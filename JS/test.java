import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

class test{
    public static void main(String[] args) {
        for (int num : test.solution(0, 3, 1, 4)) {
            System.out.println(num);
        }
    }
    public static int[] solution(int denum1, int num1, int denum2, int num2) {
        int up = denum1 * num2 + denum2 * num1;
        int down = num1 * num2;
        int num = 2;
        int small_num = num1 > num2 ? num2 : num1;
        while(num <= small_num){
            if(up == 1 || down == 1) break;
            if(up % num + down % num == 0){
                up /= num;
                down /= num;
                continue;
            }
            num++;
        }
        return new int[] {up, down};
    }

}
