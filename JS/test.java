import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class test{
    public static void main(String[] args) {
        System.out.println(Solution.solution(new int[]{0,-1,-2,-3}));
    }

}
class Solution{
    static int solution(int[] common){
        Set<Integer> plusList = new HashSet<Integer>();
        Set<Integer> productList = new HashSet<Integer>();
        for(int i = 0 ; i < common.length - 1; i++){
            plusList.add(common[i + 1] - common[i]);
            if(common[i] != 0){
                productList.add(common[i + 1] / common[i]);
            }
        }

        Iterator<Integer> iter = plusList.size() == 1 ? plusList.iterator() : productList.iterator();

        return plusList.size() == 1 ? common[common.length - 1] + iter.next() : common[common.length - 1] * iter.next();
    }
}

