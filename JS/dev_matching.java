import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class dev_matching {
    public static void main(String[] args) {
        String[] test = new String[]{
            "jack:9,10,13,9,15"
            ,"jerry:7,7,14,10,17"
            ,"jean:0,0,12,20,0"};
        for (String string : Solution.solution(test)) {
            System.out.println(string);
        }
    }

    static class Solution{
        public static ArrayList<ArrayList<Integer>> doneCountByPeopleArray = new ArrayList<>();  //코스별 완주자 수

        public static String[] solution(String[] record){
            String[] answer = new String[record.length];
            People[] people = new People[record.length];

            for(int i = 0; i < 5; i++){
                doneCountByPeopleArray.add(new ArrayList<>());
            }

            for (int i = 0; i < record.length; i++) {
                people[i] = new People(record[i]);
                for (int j = 0; j < 5; j++) {
                    if(people[i].isDoneCourse(j)){
                        doneCountByPeopleArray.get(j).add(people[i].getCourse()[j]);
                    }
                }
            }
            for (int i = 0; i < 5; i++) {
                Collections.sort(doneCountByPeopleArray.get(i));
            }

            Arrays.sort(people);

            for (int i = 0; i < answer.length; i++) {
                answer[i] = people[i].getName();                
            }

            return answer;
        }

        static class People implements Comparable<People> {
            private String name;
            private int[] course;
            private final int[] TYPE_OF_MEDAL = {0,1,2,3};//x, 동, 은, 금

            public People(String str){
                String[] splitStr = str.split(":");
                this.name = splitStr[0];
                this.course = Arrays.stream(splitStr[1].split(",")).mapToInt(Integer::parseInt).toArray();
            }

            public String getName(){
                return this.name;
            }

            public int[] getCourse(){
                return this.course;
            }

            public int getBestCourse(){
                int returnValue = 0;
                for(int i = this.course.length - 1; i > -1; i--){
                    if(this.course[i] != 0){
                        return i;
                    }
                }
                return returnValue;
            }

            public int getDoneCourse(){
                return (int)Arrays.stream(this.course).filter(e -> e != 0).count();
            }

            public boolean isDoneCourse(int courseNo){
                return this.course[courseNo] == 0 ? false : true;
            }

            public int sumOfRecord(){
                return Arrays.stream(this.course).sum();
            }

            public int getMedal(int courseNo){
                Integer[] arr = (Integer[]) Arrays.asList(doneCountByPeopleArray.get(courseNo)).toArray();
                
                return 0;
            }

            @Override
            public int compareTo(dev_matching.Solution.People o) {
                if(this.getDoneCourse() > o.getDoneCourse()){
                    return 1;
                } else if(this.getDoneCourse() < o.getDoneCourse()){
                    return -1;
                } else {    // 경기 완주 코스수가 같을경우
                    if(this.getBestCourse() > o.getBestCourse()){
                        return 1;
                    } else if(this.getBestCourse() < o.getBestCourse()){
                        return -1;
                    } else {    //어려운 코스 완주가 같을경우
                        
                    }
                }
                return 1;
            }
            

        }
    }
    
}
