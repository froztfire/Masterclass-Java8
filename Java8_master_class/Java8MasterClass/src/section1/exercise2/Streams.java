package section1.exercise2;

import java.util.Arrays;

public class Streams {

    public static void main(String[] args) {
        Integer[] numArr = { 7, 5, 901, 175, 8, 100, 21, 16, 3, 26, 34, 89, 82, 1, 1000, 101 };
//        Integer[] numArr = { 7, 5, 8, 901, 175, 100, 21, 16, 3, 26, 34, 89, 1, 1000, 101 };
        solution(numArr);
    }
    public static void solution(Integer[] numArr) {
        double[] filteredNum = Arrays.stream(numArr)
                .filter(num -> num <= 100)
                .sorted((a, b) -> b - a)
                .mapToDouble(Integer::doubleValue)
                .toArray();
        System.out.println("Filtered and sorted numbers : ");
        for (double num : filteredNum) {
            System.out.print(num + "  ");

        }
        if (filteredNum.length %2 == 0 ){
            int middleIndex = filteredNum.length / 2-1;
            double firstMiddleNumber = filteredNum[middleIndex];
            System.out.println("\nFirst Middle Number " + firstMiddleNumber);
        } else {
            int middleIndex = filteredNum.length / 2;
            double firstMiddleNumber = filteredNum[middleIndex];
            System.out.println("\nFirst Middle Number " + firstMiddleNumber);
        }
    }
}




